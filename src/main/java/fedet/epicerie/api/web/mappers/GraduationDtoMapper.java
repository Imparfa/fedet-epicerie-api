package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.web.dtos.GraduationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface GraduationDtoMapper {

    @ValueMapping(source = "BAC+2", target = "BAC_2")
    @ValueMapping(source = "BAC+3", target = "BAC_3")
    @ValueMapping(source = "BAC+5", target = "BAC_5")
    @ValueMapping(source = "BAC+8", target = "BAC_8")
    GraduationDto toDto(String graduation);

    @ValueMapping(source = "BAC_2", target = "BAC+2")
    @ValueMapping(source = "BAC_3", target = "BAC+3")
    @ValueMapping(source = "BAC_5", target = "BAC+5")
    @ValueMapping(source = "BAC_8", target = "BAC+8")
    String toEntity(GraduationDto graduationDto);
}
