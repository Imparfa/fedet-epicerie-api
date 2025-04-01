package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.domain.models.Distribution;
import fedet.epicerie.api.web.dtos.DistributionCreateEditRequestDto;
import fedet.epicerie.api.web.dtos.DistributionDto;
import org.mapstruct.Mapper;

@Mapper
public interface DistributionDtoMapper {

    DistributionDto toDto(Distribution distribution);

    Distribution toModel(DistributionDto distributionDto);

    Distribution toModel(DistributionCreateEditRequestDto distributionCreateEditRequestDto);
}
