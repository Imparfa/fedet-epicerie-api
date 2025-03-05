package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.models.Distribution;
import fedet.epicerie.api.persistence.entities.DistributionEty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DistributionMapperTest implements WithRandom {

    private final DistributionMapper distributionMapper = Mappers.getMapper(DistributionMapper.class);

    static Stream<DistributionEty> distributionEtyProvider() {
        return Stream.of(null, new DistributionEty());  // null et instance valide instantiable
    }

    @ParameterizedTest
    @MethodSource("distributionEtyProvider")
    void toModelTest(DistributionEty distributionEty) {
        // When
        Distribution distribution = distributionMapper.toModel(distributionEty);

        // Then
        if (distributionEty == null) {
            assertThat(distribution).isNull();  // Cas où l'entrée est null
        } else {
            assertThat(distribution).isNotNull();
            assertThat(distribution.getId()).isEqualTo(distributionEty.getId());
            assertThat(distribution.getName()).isEqualTo(distributionEty.getName());
            assertThat(distribution.getAddress()).isEqualTo(distributionEty.getAddress());
            assertThat(distribution.getIsActive()).isEqualTo(distributionEty.getIsActive());
        }
    }

    static Stream<Distribution> distributionProvider() {
        return Stream.of(null, Distribution.builder().build());  // null et instance valide via builder
    }

    @ParameterizedTest
    @MethodSource("distributionProvider")
    void toEntityTest(Distribution distribution) {
        // When
        DistributionEty distributionEty = distributionMapper.toEntity(distribution);

        // Then
        if (distribution == null) {
            assertThat(distributionEty).isNull();  // Cas où l'entrée est null
        } else {
            assertThat(distributionEty).isNotNull();
            assertThat(distributionEty.getId()).isEqualTo(distribution.getId());
            assertThat(distributionEty.getName()).isEqualTo(distribution.getName());
            assertThat(distributionEty.getAddress()).isEqualTo(distribution.getAddress());
            assertThat(distributionEty.getIsActive()).isEqualTo(distribution.getIsActive());
        }
    }
}
