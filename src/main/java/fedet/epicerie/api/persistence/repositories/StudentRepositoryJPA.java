package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.persistence.entities.StudentEty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepositoryJPA extends JpaRepository<StudentEty, UUID> {

    Optional<StudentEty> findByEmail(@NonNull String email);

    @Modifying
    @Transactional
    @Query("UPDATE StudentEty s SET s.lastVisit = ?1 WHERE s.id = ?2")
    void updateLastVisitById(@Nullable LocalDate lastVisit, @NonNull UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE StudentEty s SET s.lastLocation = ?1 WHERE s.id = ?2")
    void updateLastLocationById(@Nullable String lastLocation, @NonNull UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE StudentEty s SET s.qrCode = ?1 WHERE s.id = ?2")
    void updateQRCodeById(@NonNull String qrCode, @NonNull UUID id);
}