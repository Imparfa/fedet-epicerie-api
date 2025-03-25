package fedet.epicerie.api.domain.ports;

import fedet.epicerie.api.domain.models.Visit;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VisitPort {

    boolean hasVisitedToday(UUID studentId, LocalDate date);

    List<Visit> findAll();

    List<Visit> findByStudentId(UUID studentId);

    List<Visit> findByDate(LocalDate date);

    List<Visit> findByEmailAndDateBetween(String email, LocalDate start, LocalDate end);

    List<Visit> findByDateBetween(LocalDate start, LocalDate end);

    List<Visit> findByEmail(String email);

    List<Visit> findLast(Integer limit);

    Visit save(Visit visit);
}
