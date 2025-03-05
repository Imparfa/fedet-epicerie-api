package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.persistence.entities.VisitEty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VisitRepositoryJPA extends JpaRepository<VisitEty, Integer> {
    List<VisitEty> findByStudentId(@NonNull UUID studentId);

    List<VisitEty> findByVisitDate(@NonNull LocalDate visitDate);

    boolean existsByStudentIdAndVisitDate(UUID studentId, LocalDate visitDate);
}
