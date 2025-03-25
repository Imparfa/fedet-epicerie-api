package fedet.epicerie.api.web.controller;

import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.web.apis.ManagementApi;
import fedet.epicerie.api.web.dtos.StatsResponseDto;
import fedet.epicerie.api.web.dtos.StudentDto;
import fedet.epicerie.api.web.dtos.VisitDto;
import fedet.epicerie.api.web.mappers.StudentDtoMapper;
import fedet.epicerie.api.web.mappers.VisitDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@PreAuthorize("hasRole('ADMIN')")
public class ManagementController implements ManagementApi {
    private final StudentPort studentPort;
    private final VisitPort visitPort;
    private final StudentDtoMapper studentDtoMapper;
    private final VisitDtoMapper visitDtoMapper;


    @Override
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<StudentDto> studentDtos = studentPort.findAll().stream()
                .map(studentDtoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(studentDtos);
    }

    @Override
    public ResponseEntity<List<VisitDto>> getVisits(String email, LocalDate startDate, LocalDate endDate) {
        List<Visit> visits;

        if (email != null && startDate != null && endDate != null) {
            visits = visitPort.findByEmailAndDateBetween(email, startDate, endDate);
        } else if (email != null) {
            visits = visitPort.findByEmail(email);
        } else if (startDate != null && endDate != null) {
            visits = visitPort.findByDateBetween(startDate, endDate);
        } else {
            visits = visitPort.findAll();
        }

        List<VisitDto> studentDtos = visits.stream()
                .map(visitDtoMapper::toDto)
                .collect(Collectors.toList());

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
