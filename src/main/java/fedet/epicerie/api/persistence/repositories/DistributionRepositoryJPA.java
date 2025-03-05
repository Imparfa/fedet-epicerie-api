package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.persistence.entities.DistributionEty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface DistributionRepositoryJPA extends JpaRepository<DistributionEty, UUID> {
    Optional<DistributionEty> findByName(@NonNull String name);
}
