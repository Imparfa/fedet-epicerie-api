package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.web.dtos.VisitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentDtoMapper.class, GraduationDtoMapper.class, FormationDtoMapper.class})
public interface VisitDtoMapper {

    VisitDto toDto(Visit visit);

    Visit toModel(VisitDto visitDto);
}
