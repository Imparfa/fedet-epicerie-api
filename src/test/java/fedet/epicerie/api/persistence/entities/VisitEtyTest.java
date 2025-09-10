package fedet.epicerie.api.persistence.entities;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class VisitEtyTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        UUID id = randomUUID();
        LocalDate visitDate = LocalDate.now().minusDays(5);
        DistributionEty distribution = random(DistributionEty.class);
        String paymentMethod = randomString();

        // When
        VisitEty visitEty = new VisitEty();
        visitEty.setId(id);
        visitEty.setVisitDate(visitDate);
        visitEty.setDistribution(distribution);
        visitEty.setPaymentMethod(paymentMethod);

        // Then
        assertThat(visitEty).isNotNull();
        assertThat(visitEty.getId()).isEqualTo(id);
        assertThat(visitEty.getVisitDate()).isEqualTo(visitDate);
        assertThat(visitEty.getDistribution()).isEqualTo(distribution);
        assertThat(visitEty.getPaymentMethod()).isEqualTo(paymentMethod);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        VisitEty visitEty = random(VisitEty.class);
        UUID id = randomUUID();
        LocalDate visitDate = LocalDate.now().minusDays(5);
        DistributionEty distribution = random(DistributionEty.class);
        String paymentMethod = randomString();

        // When
        visitEty.setId(id);
        visitEty.setVisitDate(visitDate);
        visitEty.setDistribution(distribution);
        visitEty.setPaymentMethod(paymentMethod);

        // Then
        assertThat(visitEty.getId()).isEqualTo(id);
        assertThat(visitEty.getVisitDate()).isEqualTo(visitDate);
        assertThat(visitEty.getDistribution()).isEqualTo(distribution);
        assertThat(visitEty.getPaymentMethod()).isEqualTo(paymentMethod);
    }
}
