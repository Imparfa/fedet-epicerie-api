package fedet.epicerie.api.domain.models;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AdminTest implements WithRandom {

    @Test
    void constructorTest() {
        // Given
        UUID id = randomUUID();
        String firstname = randomString();
        String lastname = randomString();
        String email = randomString();
        String password = randomString();
        String role = randomString();

        // When
        Admin admin = Admin.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(password)
                .role(role)
                .build();

        // Then
        assertThat(admin).isNotNull();
        assertThat(admin.getId()).isEqualTo(id);
        assertThat(admin.getFirstname()).isEqualTo(firstname);
        assertThat(admin.getLastname()).isEqualTo(lastname);
        assertThat(admin.getEmail()).isEqualTo(email);
        assertThat(admin.getPassword()).isEqualTo(password);
        assertThat(admin.getRole()).isEqualTo(role);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        Admin admin = random(Admin.class);
        UUID id = randomUUID();
        String firstname = randomString();
        String lastname = randomString();
        String email = randomString();
        String password = randomString();
        String role = randomString();

        // When
        admin.setId(id);
        admin.setFirstname(firstname);
        admin.setLastname(lastname);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setRole(role);

        // Then
        assertThat(admin.getId()).isEqualTo(id);
        assertThat(admin.getFirstname()).isEqualTo(firstname);
        assertThat(admin.getLastname()).isEqualTo(lastname);
        assertThat(admin.getEmail()).isEqualTo(email);
        assertThat(admin.getPassword()).isEqualTo(password);
        assertThat(admin.getRole()).isEqualTo(role);
    }
}
