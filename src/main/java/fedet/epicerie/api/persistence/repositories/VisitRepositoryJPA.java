package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.persistence.entities.VisitEty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VisitRepositoryJPA extends JpaRepository<VisitEty, Integer> {
    List<VisitEty> findByStudentId(@NonNull UUID studentId);

    List<VisitEty> findByVisitDate(@NonNull LocalDate visitDate);

    boolean existsByStudentIdAndVisitDate(UUID studentId, LocalDate visitDate);

    List<VisitEty> findByStudentEmail(@NonNull String email);

    List<VisitEty> findByStudentEmailAndVisitDateBetween(@NonNull String email, @NonNull LocalDate start, @NonNull LocalDate end);

    List<VisitEty> findByVisitDateBetween(@NonNull LocalDate start, @NonNull LocalDate end);

    @Query("SELECT v FROM VisitEty v ORDER BY v.visitDate DESC")
    List<VisitEty> findRecentVisits(Pageable pageable);

    List<VisitEty> findByPaymentMethod(@NonNull String paymentMethod);

    List<VisitEty> findByDistributionId(@NonNull UUID distributionId);

    List<VisitEty> findByStudentFormation(String formation);
}
