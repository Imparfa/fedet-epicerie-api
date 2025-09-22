package fedet.epicerie.api.web.controllers;

import fedet.epicerie.api.domain.models.Card;
import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.ports.CardPort;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.services.CardStorageService;
import fedet.epicerie.api.domain.services.QrCodeService;
import fedet.epicerie.api.web.apis.StudentApi;
import fedet.epicerie.api.web.dtos.CardDto;
import fedet.epicerie.api.web.dtos.CardStatusDto;
import fedet.epicerie.api.web.dtos.StudentDto;
import fedet.epicerie.api.web.dtos.StudentEditRequestDto;
import fedet.epicerie.api.web.mappers.CardDtoMapper;
import fedet.epicerie.api.web.mappers.StudentDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
public class StudentController implements StudentApi {
    private final StudentPort studentPort;
    private final StudentDtoMapper studentDtoMapper;

    private final CardPort cardPort;
    private final CardDtoMapper cardDtoMapper;
    private final CardStorageService cardStorageService;
    private final QrCodeService qrCodeService;

    @Override
    public ResponseEntity<StudentDto> getProfile(@RequestParam @Nullable String email) {
        String targetEmail = isAdmin() && email != null ? email : getEmailFromBearer();

        Student existingStudent = studentPort.findByEmail(targetEmail);
        if (existingStudent == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        if (existingStudent.getQrCode() == null) {
            studentPort.updateQRCodeById(qrCodeService.generateQrCode(existingStudent.getId().toString()), existingStudent.getId());
            existingStudent = studentPort.findByEmail(targetEmail);
        }

        return ResponseEntity.ok(studentDtoMapper.toDto(existingStudent));
    }

    @Override
    public ResponseEntity<CardDto> getStudentCard(@PathVariable String id) {
        UUID studentId = UUID.fromString(id);
        if (hasAuthorisation(studentId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return cardPort.findLatestByStudentId(studentId)
                .map(card -> {
                    CardDto dto = cardDtoMapper.toDto(card);
                    String base = "/student/cards/" + card.getId() + "/image?side=";
                    dto.setStudentId(studentId);
                    dto.setFrontPath(base + "front");
                    dto.setBackPath(card.getBackPath() != null ? base + "back" : null);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<Resource> getStudentCardImage(@PathVariable UUID cardId, @RequestParam @NonNull String side) {
        Card card = cardPort.findById(cardId).orElse(null);
        if (card == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        String email = getEmailFromBearer();

        // Si admin -> il peut voir toutes les cartes
        if (!isAdmin()) {
            Student me = studentPort.findByEmail(email);
            if (me == null || !me.getId().equals(card.getStudent().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        String path = "back".equalsIgnoreCase(side) ? card.getBackPath() : card.getFrontPath();
        if (path == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Resource res = cardStorageService.asResource(path);
        if (res == null || !res.exists()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        MediaType type = (res.getFilename() != null && res.getFilename().endsWith(".png"))
                ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

        return ResponseEntity.ok().contentType(type).body(res);
    }

    @Override
    public ResponseEntity<Void> uploadStudentCard(@PathVariable String id, @RequestParam @NonNull String side, @RequestPart @NonNull MultipartFile file) {
        UUID studentId = UUID.fromString(id);
        if (hasAuthorisation(studentId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        // Récupérer l'étudiant cible (si admin => pas le même que "me")
        Student targetStudent = studentPort.findById(studentId);
        if (targetStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            // Récupère la dernière carte ou crée-en une nouvelle
            Card card = cardPort.findLatestByStudentId(studentId)
                    .orElseGet(() -> Card.builder()
                            .student(targetStudent)
                            .frontPath("") // recto sera défini si nécessaire
                            .status(CardStatusDto.PENDING.getValue())
                            .uploadedAt(LocalDate.now())
                            .build());

            // Sauvegarde du fichier
            String path = cardStorageService.save(studentId.toString(), file, side);

            if ("front".equalsIgnoreCase(side)) {
                card.setFrontPath(path);
            } else if ("back".equalsIgnoreCase(side)) {
                card.setBackPath(path);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            card.setStatus(CardStatusDto.PENDING.getValue()); // revalider à chaque update
            card.setUploadedAt(LocalDate.now());

            cardPort.save(card);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Override
    public ResponseEntity<StudentDto> editProfile(@RequestBody StudentEditRequestDto studentEditRequestDto, @RequestParam @Nullable String email) {
        String targetEmail = isAdmin() && email != null ? email : getEmailFromBearer();

        Student existingStudent = studentPort.findByEmail(targetEmail);
        if (existingStudent == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        studentDtoMapper.updateStudentFromDto(existingStudent, studentEditRequestDto, isAdmin());
        Student updatedStudent = studentPort.editStudent(existingStudent);

        return ResponseEntity.ok(studentDtoMapper.toDto(updatedStudent));
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String getEmailFromBearer() {
        return getAuthentication().getName();
    }

    private boolean isAdmin() {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    private boolean hasAuthorisation(UUID studentId) {
        String email = getEmailFromBearer();
        Student me = studentPort.findByEmail(email);

        return !(isAdmin() || (me != null && me.getId().equals(studentId)));
    }
}