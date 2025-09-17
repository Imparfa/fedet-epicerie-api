package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.domain.models.Card;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.domain.ports.CardPort;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.persistence.entities.CardEty;
import fedet.epicerie.api.persistence.entities.StudentEty;
import fedet.epicerie.api.persistence.mappers.CardMapper;
import fedet.epicerie.api.persistence.mappers.VisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CardRepository implements CardPort {
    private final CardRepositoryJPA cardRepositoryJPA;
    private final CardMapper cardMapper;

    @Override
    public List<Card> findAll() {
        return cardRepositoryJPA.findAll().stream().map(cardMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Card> findById(UUID id) {
        return cardRepositoryJPA.findById(id).map(cardMapper::toModel);
    }

    @Override
    public List<Card> findByStudentId(UUID studentId) {
        return cardRepositoryJPA.findByStudentId(studentId).stream().map(cardMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Card> findByStudentIdOrderByUploadedAt(UUID studentId) {
        return cardRepositoryJPA.findByStudentIdOrderByUploadedAtDesc(studentId).stream().map(cardMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Card> findLatestByStudentId(UUID studentId) {
        return cardRepositoryJPA.findTopByStudentIdOrderByUploadedAtDesc(studentId).map(cardMapper::toModel);
    }

    @Override
    public Page<Card> findByStatus(String status, Pageable pageable) {
        return cardRepositoryJPA.findByStatus(status, pageable).map(cardMapper::toModel);
    }

    @Override
    public long countByStatus(String status) {
        return cardRepositoryJPA.countByStatus(status);
    }

    @Override
    public Card save(Card card) {
        return cardMapper.toModel(cardRepositoryJPA.save(cardMapper.toEntity(card)));
    }
}
