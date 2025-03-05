package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.web.dtos.GraduationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraduationDtoMapperTest implements WithRandom {

    private final GraduationDtoMapper graduationDtoMapper = Mappers.getMapper(GraduationDtoMapper.class);

    // Fournisseur de paramètres pour les tests toDto
    static Stream<Object[]> toDtoProvider() {
        return Stream.of(
                new Object[]{null, null},              // Cas null
                new Object[]{"BAC+2", GraduationDto.BAC_2},
                new Object[]{"BAC+3", GraduationDto.BAC_3},
                new Object[]{"BAC+5", GraduationDto.BAC_5},
                new Object[]{"BAC+8", GraduationDto.BAC_8},
                new Object[]{"BAC_2", GraduationDto.BAC_2},
                new Object[]{"BAC_3", GraduationDto.BAC_3},
                new Object[]{"BAC_5", GraduationDto.BAC_5},
                new Object[]{"BAC_8", GraduationDto.BAC_8}
        );
    }

    @ParameterizedTest
    @MethodSource("toDtoProvider")
    void toDtoTest(String source, GraduationDto expected) {
        // When
        GraduationDto result = graduationDtoMapper.toDto(source);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    // Test d'exception pour une valeur non conforme dans toDto
    @Test
    void toDtoInvalidTest() {
        // Given
        String invalidSource = "BAC+4";

        // When + Then
        assertThrows(IllegalArgumentException.class, () -> graduationDtoMapper.toDto(invalidSource));
    }

    // Fournisseur de paramètres pour les tests toEntity
    static Stream<Object[]> toEntityProvider() {
        return Stream.of(
                new Object[]{null, null},              // Cas null
                new Object[]{GraduationDto.BAC_2, "BAC+2"},
                new Object[]{GraduationDto.BAC_3, "BAC+3"},
                new Object[]{GraduationDto.BAC_5, "BAC+5"},
                new Object[]{GraduationDto.BAC_8, "BAC+8"}
        );
    }

    @ParameterizedTest
    @MethodSource("toEntityProvider")
    void toEntityTest(GraduationDto source, String expected) {
        // When
        String result = graduationDtoMapper.toEntity(source);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    // Test d'exception pour une valeur non conforme dans toEntity
    @Test
    void toEntityInvalidTest() {
        // TODO: Given

        // When + Then
        assertThrows(IllegalArgumentException.class, () -> GraduationDto.valueOf("UNDEFINED"));
    }
}
