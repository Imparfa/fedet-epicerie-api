package fedet.epicerie.api.web.services;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class JwtServiceTest implements WithRandom {

    @Mock
    private VaultService vaultService;

    @InjectMocks
    private JwtService jwtService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        when(vaultService.getSecretKey()).thenReturn("superSecretKeyForJwtGenerationWithAtLeast256Bits");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @ParameterizedTest
    @CsvSource({
            "user@example.com, USER",
            "admin@example.com, ADMIN"
    })
    void generateTokenWithRoleTest(String email, String role) {
        // When
        String token = jwtService.generateTokenWithRole(email, role);

        // Then
        assertThat(token).isNotNull();
        assertThat(token).contains(".");
    }

    @ParameterizedTest
    @CsvSource({
            "true",
            "false"
    })
    void validateTokenTest(boolean validToken) {
        // Given
        String token = validToken ? jwtService.generateTokenWithRole("test@example.com", "USER") : "invalid.token.value";

        // When
        boolean isValid = jwtService.validateToken(token);

        // Then
        assertThat(isValid).isEqualTo(validToken);
    }

    @Test
    void extractEmailTest() {
        // Given
        String email = "test@example.com";
        String token = jwtService.generateTokenWithRole(email, "USER");

        // When
        String extractedEmail = jwtService.extractEmail(token);

        // Then
        assertThat(extractedEmail).isEqualTo(email);
    }

    @Test
    void extractRoleTest() {
        // Given
        String role = "ADMIN";
        String token = jwtService.generateTokenWithRole("test@example.com", role);

        // When
        String extractedRole = jwtService.extractRole(token);

        // Then
        assertThat(extractedRole).isEqualTo(role);
    }

    @Test
    void invalidTokenThrowsExceptionTest() {
        // Given
        String invalidToken = "invalid.token.value";

        // When / Then
        assertThrows(io.jsonwebtoken.JwtException.class, () -> jwtService.extractEmail(invalidToken));
    }
}
