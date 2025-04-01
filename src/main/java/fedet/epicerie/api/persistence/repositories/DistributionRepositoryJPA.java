package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.persistence.entities.DistributionEty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface DistributionRepositoryJPA extends JpaRepository<DistributionEty, UUID> {
    Optional<DistributionEty> findByName(@NonNull String name);

    @Override
    void deleteById(@NonNull UUID uuid);

    @Transactional
    @Modifying
    @Query("update DistributionEty d set d.name = ?1, d.address = ?2, d.isActive = ?3 where d.id = ?4")
    int updateNameAndAddressAndIsActiveById(@NonNull String name, String address, @NonNull Boolean isActive, @NonNull UUID id);
}
