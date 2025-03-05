package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.persistence.mappers.VisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VisitRepository implements VisitPort {
    private final VisitRepositoryJPA visitRepositoryJPA;
    private final VisitMapper visitMapper;

    @Override
    public boolean hasVisitedToday(UUID studentId, LocalDate date) {
        return visitRepositoryJPA.existsByStudentIdAndVisitDate(studentId, date);
    }

    @Override
    public List<Visit> findAll() {
        return visitRepositoryJPA.findAll().stream()
                .map(visitMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByStudentId(UUID studentId) {
        return visitRepositoryJPA.findByStudentId(studentId).stream()
                .map(visitMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDate(LocalDate date) {
        return visitRepositoryJPA.findByVisitDate(date).stream()
                .map(visitMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Visit save(Visit visit) {
        return visitMapper.toModel(
                visitRepositoryJPA.save(visitMapper.toEntity(visit))
        );
    }
}
