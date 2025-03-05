package fedet.epicerie.api.domain.models;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        UUID id = randomUUID();
        String firstname = randomString();
        String lastname = randomString();
        String email = randomString();
        String password = randomString();
        String formation = randomString();
        String graduation = randomString();
        String lastLocation = randomString();
        String qrCode = randomString();
        boolean isStudent = randomBoolean();
        boolean isWorker = randomBoolean();
        int household = randomInt() % 10 + 1; // Entre 1 et 10
        LocalDate birthdate = LocalDate.now().minusYears(20);
        LocalDate createdAt = LocalDate.now();
        LocalDate lastVisit = LocalDate.now().minusDays(5);

        // When
        Student student = Student.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(password)
                .formation(formation)
                .graduation(graduation)
                .lastLocation(lastLocation)
                .qrCode(qrCode)
                .isStudent(isStudent)
                .isWorker(isWorker)
                .household(household)
                .birthdate(birthdate)
                .createdAt(createdAt)
                .lastVisit(lastVisit)
                .build();

        // Then
        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(id);
        assertThat(student.getFirstname()).isEqualTo(firstname);
        assertThat(student.getLastname()).isEqualTo(lastname);
        assertThat(student.getEmail()).isEqualTo(email);
        assertThat(student.getPassword()).isEqualTo(password);
        assertThat(student.getFormation()).isEqualTo(formation);
        assertThat(student.getGraduation()).isEqualTo(graduation);
        assertThat(student.getLastLocation()).isEqualTo(lastLocation);
        assertThat(student.getQrCode()).isEqualTo(qrCode);
        assertThat(student.getBirthdate()).isEqualTo(birthdate);
        assertThat(student.getCreatedAt()).isEqualTo(createdAt);
        assertThat(student.getLastVisit()).isEqualTo(lastVisit);
        assertThat(student.getIsStudent()).isEqualTo(isStudent);
        assertThat(student.getIsWorker()).isEqualTo(isWorker);
        assertThat(student.getHousehold()).isEqualTo(household);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        Student student = random(Student.class);
        String email = randomString();
        String formation = randomString();
        String qrCode = randomString();
        String lastLocation = randomString();
        boolean isStudent = randomBoolean();
        boolean isWorker = randomBoolean();
        int household = randomInt() % 10 + 1;

        // When
        student.setEmail(email);
        student.setFormation(formation);
        student.setQrCode(qrCode);
        student.setLastLocation(lastLocation);
        student.setIsStudent(isStudent);
        student.setIsWorker(isWorker);
        student.setHousehold(household);

        // Then
        assertThat(student.getEmail()).isEqualTo(email);
        assertThat(student.getFormation()).isEqualTo(formation);
        assertThat(student.getQrCode()).isEqualTo(qrCode);
        assertThat(student.getLastLocation()).isEqualTo(lastLocation);
        assertThat(student.getIsStudent()).isEqualTo(isStudent);
        assertThat(student.getIsWorker()).isEqualTo(isWorker);
        assertThat(student.getHousehold()).isEqualTo(household);
    }
}
