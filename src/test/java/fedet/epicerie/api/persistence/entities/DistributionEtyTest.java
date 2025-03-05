package fedet.epicerie.api.persistence.entities;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DistributionEtyTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        UUID id = randomUUID();
        String name = randomString();
        String address = randomString();
        Boolean isActive = randomBoolean();

        // When
        DistributionEty distributionEty = new DistributionEty();
        distributionEty.setId(id);
        distributionEty.setName(name);
        distributionEty.setAddress(address);
        distributionEty.setIsActive(isActive);

        // Then
        assertThat(distributionEty).isNotNull();
        assertThat(distributionEty.getId()).isEqualTo(id);
        assertThat(distributionEty.getName()).isEqualTo(name);
        assertThat(distributionEty.getAddress()).isEqualTo(address);
        assertThat(distributionEty.getIsActive()).isEqualTo(isActive);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        DistributionEty distributionEty = random(DistributionEty.class);
        UUID id = randomUUID();
        String name = randomString();
        String address = randomString();
        Boolean isActive = randomBoolean();

        // When
        distributionEty.setId(id);
        distributionEty.setName(name);
        distributionEty.setAddress(address);
        distributionEty.setIsActive(isActive);

        // Then
        assertThat(distributionEty.getId()).isEqualTo(id);
        assertThat(distributionEty.getName()).isEqualTo(name);
        assertThat(distributionEty.getAddress()).isEqualTo(address);
        assertThat(distributionEty.getIsActive()).isEqualTo(isActive);
    }
}
