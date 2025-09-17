package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.persistence.entities.CardEty;
import fedet.epicerie.api.persistence.entities.VisitEty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardRepositoryJPA extends JpaRepository<CardEty, UUID> {
    List<CardEty> findByStudentId(@NonNull UUID studentId);
    List<CardEty> findByStudentIdOrderByUploadedAt(UUID studentId);

    List<CardEty> findByStudentIdOrderByUploadedAtDesc(UUID studentId);

    Page<CardEty> findByStatus(String status, Pageable pageable);
    long countByStatus(String status);

    Optional<CardEty> findTopByStudentIdOrderByUploadedAtDesc(UUID studentId);
}
