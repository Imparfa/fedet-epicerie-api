package fedet.epicerie.api.domain.models;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class VisitTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        int id = randomInt();
        Student student = random(Student.class);
        LocalDate visitDate = LocalDate.now().minusDays(5);
        String location = randomString();
        String paymentMethod = randomString();

        // When
        Visit visit = Visit.builder()
                .id(id)
                .student(student)
                .visitDate(visitDate)
                .location(location)
                .paymentMethod(paymentMethod)
                .build();

        // Then
        assertThat(visit).isNotNull();
        assertThat(visit.getId()).isEqualTo(id);
        assertThat(visit.getStudent()).isEqualTo(student);
        assertThat(visit.getVisitDate()).isEqualTo(visitDate);
        assertThat(visit.getLocation()).isEqualTo(location);
        assertThat(visit.getPaymentMethod()).isEqualTo(paymentMethod);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        Visit visit = random(Visit.class);
        int id = randomInt();
        Student student = random(Student.class);
        LocalDate visitDate = LocalDate.now().minusDays(5);
        String location = randomString();
        String paymentMethod = randomString();

        // When
        visit.setId(id);
        visit.setStudent(student);
        visit.setVisitDate(visitDate);
        visit.setLocation(location);
        visit.setPaymentMethod(paymentMethod);

        // Then
        assertThat(visit.getId()).isEqualTo(id);
        assertThat(visit.getStudent()).isEqualTo(student);
        assertThat(visit.getVisitDate()).isEqualTo(visitDate);
        assertThat(visit.getLocation()).isEqualTo(location);
        assertThat(visit.getPaymentMethod()).isEqualTo(paymentMethod);
    }
}
