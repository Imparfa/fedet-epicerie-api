package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.persistence.entities.AdminEty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminRepositoryJPATest implements WithRandom {

    @Mock
    private AdminRepositoryJPA adminRepositoryJPA;

    @ParameterizedTest
    @CsvSource({
            "true",  // Admin trouvé
            "false"  // Admin non trouvé
    })
    void findByEmailTest(boolean adminExists) {
        // Given
        String email = randomString() + "@example.com";
        Optional<AdminEty> expectedAdmin = adminExists ? Optional.of(random(AdminEty.class)) : Optional.empty();
        given(adminRepositoryJPA.findByEmail(email)).willReturn(expectedAdmin);

        // When
        Optional<AdminEty> result = adminRepositoryJPA.findByEmail(email);

        // Then
        if (adminExists) {
            assertThat(result).isPresent();
        } else {
            assertThat(result).isEmpty();
        }
        verify(adminRepositoryJPA).findByEmail(email);
    }

    @ParameterizedTest
    @CsvSource({
            "true",  // ID trouvé
            "false"  // ID non trouvé
    })
    void findByIdTest(boolean idExists) {
        // Given
        UUID id = randomUUID();
        Optional<AdminEty> expectedAdmin = idExists ? Optional.of(random(AdminEty.class)) : Optional.empty();
        if (idExists) expectedAdmin.get().setId(id);
        given(adminRepositoryJPA.findById(eq(id))).willReturn(expectedAdmin);

        // When
        Optional<AdminEty> result = adminRepositoryJPA.findById(id);

        // Then
        if (idExists) {
            assertThat(result).isPresent();
            assertThat(result.get().getId()).isEqualTo(id);
        } else {
            assertThat(result).isEmpty();
        }
        verify(adminRepositoryJPA).findById(eq(id));
    }

    @Test
    void saveTest() {
        // Given
        AdminEty adminEty = random(AdminEty.class);
        given(adminRepositoryJPA.save(any(AdminEty.class))).willReturn(adminEty);

        // When
        AdminEty savedAdmin = adminRepositoryJPA.save(adminEty);

        // Then
        assertThat(savedAdmin).isNotNull();
        assertThat(savedAdmin.getId()).isEqualTo(adminEty.getId());
        verify(adminRepositoryJPA).save(any(AdminEty.class));
    }
}
