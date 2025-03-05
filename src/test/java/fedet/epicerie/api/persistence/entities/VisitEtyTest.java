package fedet.epicerie.api.persistence.entities;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class VisitEtyTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        int id = randomInt();
        LocalDate visitDate = LocalDate.now().minusDays(5);
        String location = randomString();
        String paymentMethod = randomString();

        // When
        VisitEty visitEty = new VisitEty();
        visitEty.setId(id);
        visitEty.setVisitDate(visitDate);
        visitEty.setLocation(location);
        visitEty.setPaymentMethod(paymentMethod);

        // Then
        assertThat(visitEty).isNotNull();
        assertThat(visitEty.getId()).isEqualTo(id);
        assertThat(visitEty.getVisitDate()).isEqualTo(visitDate);
        assertThat(visitEty.getLocation()).isEqualTo(location);
        assertThat(visitEty.getPaymentMethod()).isEqualTo(paymentMethod);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        VisitEty visitEty = random(VisitEty.class);
        int id = randomInt();
        LocalDate visitDate = LocalDate.now().minusDays(5);
        String location = randomString();
        String paymentMethod = randomString();

        // When
        visitEty.setId(id);
        visitEty.setVisitDate(visitDate);
        visitEty.setLocation(location);
        visitEty.setPaymentMethod(paymentMethod);

        // Then
        assertThat(visitEty.getId()).isEqualTo(id);
        assertThat(visitEty.getVisitDate()).isEqualTo(visitDate);
        assertThat(visitEty.getLocation()).isEqualTo(location);
        assertThat(visitEty.getPaymentMethod()).isEqualTo(paymentMethod);
    }
}
