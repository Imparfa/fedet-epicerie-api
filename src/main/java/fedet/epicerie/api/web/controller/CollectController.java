package fedet.epicerie.api.web.controller;

import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.web.apis.CollectApi;
import fedet.epicerie.api.web.dtos.StudentDto;
import fedet.epicerie.api.web.dtos.ValidateCollectRequestDto;
import fedet.epicerie.api.web.mappers.StudentDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@PreAuthorize("hasRole('ADMIN')")
public class CollectController implements CollectApi {
    private final StudentPort studentPort;
    private final VisitPort visitPort;
    private final StudentDtoMapper studentDtoMapper;

    @Override
    public ResponseEntity<StudentDto> collectScan(@RequestParam String qrCode) {
        Student student = studentPort.findById(UUID.fromString(qrCode));
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        student.setQrCode(null);
        return ResponseEntity.ok(studentDtoMapper.toDto(student));
    }

    @Override
    public ResponseEntity<Void> collectValidate(ValidateCollectRequestDto requestDto) {
        Student student = studentPort.findById(UUID.fromString(requestDto.getStudentId()));
        if (student != null) {
            if (visitPort.hasVisitedToday(UUID.fromString(requestDto.getStudentId()), LocalDate.now()))
                return ResponseEntity.status(HttpStatus.CONFLICT).build();

            Visit visit = Visit.builder()
                    .student(student)
                    .visitDate(LocalDate.now())
                    .location(requestDto.getLocation())
                    .paymentMethod(requestDto.getPaymentMethod().getValue())
                    .build();
            studentPort.updateLastVisitById(visit.getVisitDate(), UUID.fromString(requestDto.getStudentId()));
            studentPort.updateLastLocationById(visit.getLocation(), UUID.fromString(requestDto.getStudentId()));

            visitPort.save(visit);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
