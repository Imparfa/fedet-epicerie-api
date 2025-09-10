package fedet.epicerie.api.domain.models;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class VisitTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        UUID id = randomUUID();
        Student student = random(Student.class);
        LocalDate visitDate = LocalDate.now().minusDays(5);
        Distribution distribution = Distribution.builder().id(randomUUID())
                .name(randomString()).address(randomString()).build();
        String paymentMethod = randomString();

        // When
        Visit visit = Visit.builder()
                .id(id)
                .student(student)
                .visitDate(visitDate)
                .distribution(distribution)
                .paymentMethod(paymentMethod)
                .build();

        // Then
        assertThat(visit).isNotNull();
        assertThat(visit.getId()).isEqualTo(id);
        assertThat(visit.getStudent()).isEqualTo(student);
        assertThat(visit.getVisitDate()).isEqualTo(visitDate);
        assertThat(visit.getDistribution()).isEqualTo(distribution);
        assertThat(visit.getPaymentMethod()).isEqualTo(paymentMethod);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        Visit visit = random(Visit.class);
        UUID id = randomUUID();
        Student student = random(Student.class);
        LocalDate visitDate = LocalDate.now().minusDays(5);
        Distribution distribution = Distribution.builder().id(randomUUID())
                .name(randomString()).address(randomString()).build();
        String paymentMethod = randomString();

        // When
        visit.setId(id);
        visit.setStudent(student);
        visit.setVisitDate(visitDate);
        visit.setDistribution(distribution);
        visit.setPaymentMethod(paymentMethod);

        // Then
        assertThat(visit.getId()).isEqualTo(id);
        assertThat(visit.getStudent()).isEqualTo(student);
        assertThat(visit.getVisitDate()).isEqualTo(visitDate);
        assertThat(visit.getDistribution()).isEqualTo(distribution);
        assertThat(visit.getPaymentMethod()).isEqualTo(paymentMethod);
    }
}
