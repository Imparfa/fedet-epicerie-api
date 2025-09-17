package fedet.epicerie.api.domain.ports;

import fedet.epicerie.api.domain.models.Card;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.persistence.entities.CardEty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardPort {

    List<Card> findAll();

    Optional<Card> findById(UUID id);

    List<Card> findByStudentId(UUID studentId);

    List<Card> findByStudentIdOrderByUploadedAt(UUID studentId);

    Optional<Card> findLatestByStudentId(UUID studentId);

    Page<Card> findByStatus(String status, Pageable pageable);

    long countByStatus(String status);

    Card save(Card card);

}
