package fedet.epicerie.api.web.controller;

import fedet.epicerie.api.domain.models.Distribution;
import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.domain.ports.DistributionPort;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.web.apis.ManagementApi;
import fedet.epicerie.api.web.dtos.*;
import fedet.epicerie.api.web.mappers.DistributionDtoMapper;
import fedet.epicerie.api.web.mappers.StudentDtoMapper;
import fedet.epicerie.api.web.mappers.VisitDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@PreAuthorize("hasRole('ADMIN')")
public class ManagementController implements ManagementApi {
    private final StudentPort studentPort;
    private final DistributionPort distributionPort;
    private final VisitPort visitPort;
    private final StudentDtoMapper studentDtoMapper;
    private final VisitDtoMapper visitDtoMapper;
    private final DistributionDtoMapper distributionDtoMapper;

    @Override
    public ResponseEntity<List<StudentDto>> getStudents(Integer page, Integer size, String search) {
        int effectivePage = (page != null && page >= 0) ? page : 0;
        int effectiveSize = (size != null && size > 0) ? size : 20;

        Pageable pageable = PageRequest.of(effectivePage, effectiveSize, Sort.by("lastname").ascending());

        Page<Student> studentPage;

        if (search != null && !search.isBlank()) {
            studentPage = studentPort.searchByNameOrEmail(search.toLowerCase(), pageable);
        } else {
            studentPage = studentPort.findAll(pageable);
        }

        List<StudentDto> studentDtos = studentPage.getContent().stream()
                .peek(student -> student.setQrCode(null))
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
                .map(visit -> {
                    Student erasedQr = visit.getStudent();
                    erasedQr.setQrCode(null);
                    visit.setStudent(erasedQr);
                    return visitDtoMapper.toDto(visit);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(studentDtos);
    }

    @Override
    public ResponseEntity<DistributionDto> updateDistribution(String id, DistributionCreateEditRequestDto distributionCreateEditRequestDto) {
        UUID targetId = UUID.fromString(id);
        Distribution distribution = distributionPort.findById(targetId);
        if (distribution != null) {
            distribution = distributionDtoMapper.toModel(distributionCreateEditRequestDto);
            distribution.setId(targetId);
            distributionPort.updateById(targetId, distribution);
            return ResponseEntity.ok(distributionDtoMapper.toDto(distribution));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<DistributionDto> createDistribution(DistributionCreateEditRequestDto distributionCreateEditRequestDto) {
        Distribution newDistribution = Distribution.builder()
                .name(distributionCreateEditRequestDto.getName())
                .address(distributionCreateEditRequestDto.getAddress())
                .isActive(distributionCreateEditRequestDto.getIsActive())
                .build();
        return ResponseEntity.ok(distributionDtoMapper.toDto(distributionPort.save(newDistribution)));
    }

    @Override
    public ResponseEntity<Void> deleteDistribution(String id) {
        UUID targetId = UUID.fromString(id);
        if (distributionPort.findById(targetId) == null)
            return ResponseEntity.notFound().build();
        distributionPort.delete(targetId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<DistributionDto>> getDistributions() {
        return ResponseEntity.ok(distributionPort.findAll().stream().map(distributionDtoMapper::toDto).collect(Collectors.toList()));
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
