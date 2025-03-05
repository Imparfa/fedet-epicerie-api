package fedet.epicerie.api.web.services;

import fedet.epicerie.api.common.utils.WithRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtServiceTest implements WithRandom {

    @InjectMocks
    private JwtService jwtService = new JwtService();

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
