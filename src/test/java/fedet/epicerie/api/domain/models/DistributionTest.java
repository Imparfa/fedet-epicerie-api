package fedet.epicerie.api.domain.models;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DistributionTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        UUID id = randomUUID();
        String name = randomString();
        String address = randomString();
        Boolean isActive = randomBoolean();

        // When
        Distribution distribution = Distribution.builder()
                .id(id)
                .name(name)
                .address(address)
                .isActive(isActive)
                .build();

        // Then
        assertThat(distribution).isNotNull();
        assertThat(distribution.getId()).isEqualTo(id);
        assertThat(distribution.getName()).isEqualTo(name);
        assertThat(distribution.getAddress()).isEqualTo(address);
        assertThat(distribution.getIsActive()).isEqualTo(isActive);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        Distribution distribution = Distribution.builder().id(randomUUID())
                .name(randomString()).address(randomString()).build();
        UUID id = randomUUID();
        String name = randomString();
        String address = randomString();
        Boolean isActive = randomBoolean();

        // When
        distribution.setId(id);
        distribution.setName(name);
        distribution.setAddress(address);
        distribution.setIsActive(isActive);

        // Then
        assertThat(distribution.getId()).isEqualTo(id);
        assertThat(distribution.getName()).isEqualTo(name);
        assertThat(distribution.getAddress()).isEqualTo(address);
        assertThat(distribution.getIsActive()).isEqualTo(isActive);
    }
}