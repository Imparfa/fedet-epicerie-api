package fedet.epicerie.api.web.controller;

import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.web.apis.CollectApi;
import fedet.epicerie.api.web.dtos.FormationDto;
import fedet.epicerie.api.web.dtos.GraduationDto;
import fedet.epicerie.api.web.dtos.StudentDto;
import fedet.epicerie.api.web.dtos.ValidateCollectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CollectController implements CollectApi {
    private final StudentPort studentPort;
    private final VisitPort visitPort;

    @Override
    public ResponseEntity<StudentDto> collectScan(@RequestParam String qrCode) {
        Student student = studentPort.findById(UUID.fromString(qrCode));
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        StudentDto studentDto = new StudentDto()
                .firstname(student.getFirstname())
                .lastname(student.getLastname())
                .email(student.getEmail())
                .birthdate(student.getBirthdate())
                .formation(FormationDto.fromValue(student.getFormation()))
                .graduation(GraduationDto.fromValue(student.getGraduation()))
                .isStudent(student.getIsStudent())
                .isWorker(student.getIsWorker())
                .household(student.getHousehold());

        return ResponseEntity.ok(studentDto);
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
