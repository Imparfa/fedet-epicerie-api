package fedet.epicerie.api.domain.ports;

import fedet.epicerie.api.domain.models.Distribution;

import java.util.List;
import java.util.UUID;

public interface DistributionPort {
    List<Distribution> findAll();

    Distribution findById(UUID id);

    Distribution findByName(String name);

    Distribution save(Distribution distribution);
}
