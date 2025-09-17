package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.web.dtos.CardStatusDto;
import fedet.epicerie.api.web.dtos.GraduationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface CardStatusDtoMapper {

    CardStatusDto toDto(String cardStatus);

    String toEntity(CardStatusDto cardStatusDto);
}
