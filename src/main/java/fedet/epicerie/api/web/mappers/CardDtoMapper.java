package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.domain.models.Card;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.web.dtos.CardDto;
import fedet.epicerie.api.web.dtos.VisitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CardStatusDtoMapper.class)
public interface CardDtoMapper {

    CardDto toDto(Card card);

    Card toModel(CardDto cardDto);
}
