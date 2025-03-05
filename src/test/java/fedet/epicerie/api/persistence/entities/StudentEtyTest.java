package fedet.epicerie.api.persistence.entities;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class StudentEtyTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        UUID id = randomUUID();
        String firstname = randomString();
        String lastname = randomString();
        String email = randomString();
        LocalDate birthdate = LocalDate.now().minusYears(20);

        // When
        StudentEty studentEty = new StudentEty();
        studentEty.setId(id);
        studentEty.setFirstname(firstname);
        studentEty.setLastname(lastname);
        studentEty.setEmail(email);
        studentEty.setBirthdate(birthdate);

        // Then
        assertThat(studentEty).isNotNull();
        assertThat(studentEty.getId()).isEqualTo(id);
        assertThat(studentEty.getFirstname()).isEqualTo(firstname);
        assertThat(studentEty.getLastname()).isEqualTo(lastname);
        assertThat(studentEty.getEmail()).isEqualTo(email);
        assertThat(studentEty.getBirthdate()).isEqualTo(birthdate);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        StudentEty studentEty = random(StudentEty.class);
        UUID id = randomUUID();
        String firstname = randomString();
        String lastname = randomString();
        String email = randomString();

        // When
        studentEty.setId(id);
        studentEty.setFirstname(firstname);
        studentEty.setLastname(lastname);
        studentEty.setEmail(email);

        // Then
        assertThat(studentEty.getId()).isEqualTo(id);
        assertThat(studentEty.getFirstname()).isEqualTo(firstname);
        assertThat(studentEty.getLastname()).isEqualTo(lastname);
        assertThat(studentEty.getEmail()).isEqualTo(email);
    }
}
