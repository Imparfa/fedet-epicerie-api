package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.domain.models.Distribution;
import fedet.epicerie.api.persistence.entities.DistributionEty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DistributionMapper {
    Distribution toModel(DistributionEty entity);

    DistributionEty toEntity(Distribution model);
}
