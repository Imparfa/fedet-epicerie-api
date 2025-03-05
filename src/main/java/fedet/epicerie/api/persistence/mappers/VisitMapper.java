package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.persistence.entities.VisitEty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitMapper {

    Visit toModel(VisitEty entity);

    VisitEty toEntity(Visit model);
}
