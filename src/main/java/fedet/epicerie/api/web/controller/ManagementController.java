package fedet.epicerie.api.web.controller;

import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.web.apis.ManagementApi;
import fedet.epicerie.api.web.dtos.FormationDto;
import fedet.epicerie.api.web.dtos.GraduationDto;
import fedet.epicerie.api.web.dtos.StatsResponseDto;
import fedet.epicerie.api.web.dtos.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ManagementController implements ManagementApi {
    private final StudentPort studentPort;
    private final VisitPort visitPort;

    @Override
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<Student> students = studentPort.findAll();
        List<StudentDto> studentDtos = students.stream().map(student ->
                new StudentDto()
                        .firstname(student.getFirstname())
                        .lastname(student.getLastname())
                        .email(student.getEmail())
                        .birthdate(student.getBirthdate())
                        .formation(FormationDto.valueOf(student.getFormation()))
                        .graduation(GraduationDto.fromValue(student.getGraduation()))
                        .isStudent(student.getIsStudent())
                        .isWorker(student.getIsWorker())
                        .household(student.getHousehold())
        ).collect(Collectors.toList());

        return ResponseEntity.ok(studentDtos);
    }

    @Override
    public ResponseEntity<StatsResponseDto> getStats() {
        long totalStudents = studentPort.findAll().size();
        long totalVisits = visitPort.findAll().size();
        long visitsToday = visitPort.findByDate(LocalDate.now()).size();

        StatsResponseDto stats = new StatsResponseDto()
                .totalStudents((int) totalStudents)
                .totalVisits((int) totalVisits)
                .visitsToday((int) visitsToday);

        return ResponseEntity.ok(stats);
    }
}
