package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.persistence.entities.AdminEty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepositoryJPA extends JpaRepository<AdminEty, UUID> {
    Optional<AdminEty> findByEmail(@NonNull String email);
}
