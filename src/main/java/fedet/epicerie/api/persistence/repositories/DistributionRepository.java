package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.domain.models.Distribution;
import fedet.epicerie.api.domain.ports.DistributionPort;
import fedet.epicerie.api.persistence.mappers.DistributionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DistributionRepository implements DistributionPort {
    private final DistributionRepositoryJPA distributionRepositoryJPA;
    private final DistributionMapper distributionMapper;

    @Override
    public List<Distribution> findAll() {
        return distributionRepositoryJPA.findAll().stream()
                .map(distributionMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Distribution findById(UUID id) {
        return distributionRepositoryJPA.findById(id)
                .map(distributionMapper::toModel)
                .orElse(null);
    }

    @Override
    public Distribution findByName(String name) {
        return distributionRepositoryJPA.findByName(name)
                .map(distributionMapper::toModel)
                .orElse(null);
    }

    @Override
    public int updateById(UUID id, Distribution distribution) {
        return distributionRepositoryJPA.updateNameAndAddressAndIsActiveById(distribution.getName(), distribution.getAddress(), distribution.getIsActive(), id);
    }

    @Override
    public Distribution save(Distribution distribution) {
        return distributionMapper.toModel(
                distributionRepositoryJPA.save(distributionMapper.toEntity(distribution))
        );
    }

    @Override
    public void delete(UUID id) {
        distributionRepositoryJPA.deleteById(id);
    }
}