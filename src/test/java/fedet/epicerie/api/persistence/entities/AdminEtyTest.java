package fedet.epicerie.api.persistence.entities;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AdminEtyTest implements WithRandom {

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
        AdminEty adminEty = new AdminEty();
        adminEty.setId(id);
        adminEty.setFirstname(firstname);
        adminEty.setLastname(lastname);
        adminEty.setEmail(email);
        adminEty.setPassword(password);
        adminEty.setRole(role);

        // Then
        assertThat(adminEty).isNotNull();
        assertThat(adminEty.getId()).isEqualTo(id);
        assertThat(adminEty.getFirstname()).isEqualTo(firstname);
        assertThat(adminEty.getLastname()).isEqualTo(lastname);
        assertThat(adminEty.getEmail()).isEqualTo(email);
        assertThat(adminEty.getPassword()).isEqualTo(password);
        assertThat(adminEty.getRole()).isEqualTo(role);
    }

    @Test
    void gettersAndSettersTest() {
        // Given
        AdminEty adminEty = random(AdminEty.class);
        UUID id = randomUUID();
        String firstname = randomString();
        String lastname = randomString();
        String email = randomString();
        String password = randomString();
        String role = randomString();

        // When
        adminEty.setId(id);
        adminEty.setFirstname(firstname);
        adminEty.setLastname(lastname);
        adminEty.setEmail(email);
        adminEty.setPassword(password);
        adminEty.setRole(role);

        // Then
        assertThat(adminEty.getId()).isEqualTo(id);
        assertThat(adminEty.getFirstname()).isEqualTo(firstname);
        assertThat(adminEty.getLastname()).isEqualTo(lastname);
        assertThat(adminEty.getEmail()).isEqualTo(email);
        assertThat(adminEty.getPassword()).isEqualTo(password);
        assertThat(adminEty.getRole()).isEqualTo(role);
    }
}
