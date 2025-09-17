package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.domain.models.Card;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.persistence.entities.CardEty;
import fedet.epicerie.api.persistence.entities.VisitEty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card toModel(CardEty entity);

    CardEty toEntity(Card model);
}
