package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.web.dtos.FormationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface FormationDtoMapper {

    @ValueMapping(source = "BTS PI", target = "BTS_PI")
    @ValueMapping(source = "GENIE BIOLOGIQUE", target = "GENIE_BIOLOGIQUE")
    @ValueMapping(source = "TECHNIQUE DE COMMUNICATION", target = "TECHNIQUE_DE_COMMUNICATION")
    @ValueMapping(source = "ECO GESTION", target = "ECO_GESTION")
    @ValueMapping(source = "CROIX ROUGE", target = "CROIX_ROUGE")
    @ValueMapping(source = "CAMPUS EDUCTIVE", target = "CAMPUS_EDUCTIVE")
    FormationDto toDto(String formation);

    @ValueMapping(source = "BTS_PI", target = "BTS PI")
    @ValueMapping(source = "GENIE_BIOLOGIQUE", target = "GENIE BIOLOGIQUE")
    @ValueMapping(source = "TECHNIQUE_DE_COMMUNICATION", target = "TECHNIQUE DE COMMUNICATION")
    @ValueMapping(source = "ECO_GESTION", target = "ECO GESTION")
    @ValueMapping(source = "CROIX_ROUGE", target = "CROIX ROUGE")
    @ValueMapping(source = "CAMPUS_EDUCTIVE", target = "CAMPUS EDUCTIVE")
    String toEntity(FormationDto formationDto);
}

//        - BTS PI
//        - GENIE BIOLOGIQUE
//        - TECHNIQUE DE COMMUNICATION
//        - ECO GESTION
//        - CROIX ROUGE
//        - CAMPUS EDUCTIVE