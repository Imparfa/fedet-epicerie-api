package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.persistence.entities.DistributionEty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DistributionRepositoryJPATest implements WithRandom {

    @Mock
    private DistributionRepositoryJPA distributionRepositoryJPA;

    @Test
    void saveTest() {
        // Given
        DistributionEty distributionEty = random(DistributionEty.class);
        given(distributionRepositoryJPA.save(any(DistributionEty.class))).willReturn(distributionEty);

        // When
        DistributionEty savedDistribution = distributionRepositoryJPA.save(distributionEty);

        // Then
        assertThat(savedDistribution).isNotNull();
        assertThat(savedDistribution.getId()).isEqualTo(distributionEty.getId());
        verify(distributionRepositoryJPA).save(any(DistributionEty.class));
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void findByIdTest(boolean exists) {
        // Given
        UUID id = randomUUID();
        DistributionEty expectedDistribution = exists ? random(DistributionEty.class) : null;
        if (exists) expectedDistribution.setId(id);
        given(distributionRepositoryJPA.findById(eq(id))).willReturn(Optional.ofNullable(expectedDistribution));

        // When
        Optional<DistributionEty> result = distributionRepositoryJPA.findById(id);

        // Then
        if (exists) {
            assertThat(result).isPresent();
            assertThat(result.get().getId()).isEqualTo(id);
        } else {
            assertThat(result).isEmpty();
        }
        verify(distributionRepositoryJPA).findById(eq(id));
    }

    @Test
    void deleteByIdTest() {
        // Given
        UUID id = randomUUID();

        // When
        distributionRepositoryJPA.deleteById(id);

        // Then
        verify(distributionRepositoryJPA).deleteById(id);
    }
}
