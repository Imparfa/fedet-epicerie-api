package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.persistence.entities.VisitEty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitRepositoryJPATest implements WithRandom {

    @Mock
    private VisitRepositoryJPA visitRepositoryJPA;

    @ParameterizedTest
    @CsvSource({
            "true",  // Visite trouvée
            "false"  // Aucune visite trouvée
    })
    void findByStudentIdTest(boolean visitExists) {
        // Given
        UUID studentId = randomUUID();
        List<VisitEty> visits = visitExists ? List.of(random(VisitEty.class)) : List.of();
        given(visitRepositoryJPA.findByStudentId(eq(studentId))).willReturn(visits);

        // When
        List<VisitEty> result = visitRepositoryJPA.findByStudentId(studentId);

        // Then
        assertThat(result).hasSize(visitExists ? 1 : 0);
        verify(visitRepositoryJPA).findByStudentId(eq(studentId));
    }

    @ParameterizedTest
    @CsvSource({
            "true",  // Visit trouvée
            "false"  // Visit non trouvée
    })
    void findByVisitDateTest(boolean visitExists) {
        // Given
        LocalDate visitDate = LocalDate.now();
        List<VisitEty> visits = visitExists ? List.of(random(VisitEty.class)) : List.of();
        given(visitRepositoryJPA.findByVisitDate(eq(visitDate))).willReturn(visits);

        // When
        List<VisitEty> result = visitRepositoryJPA.findByVisitDate(visitDate);

        // Then
        assertThat(result).hasSize(visitExists ? 1 : 0);
        verify(visitRepositoryJPA).findByVisitDate(eq(visitDate));
    }

    @ParameterizedTest
    @CsvSource({
            "true",  // Visit existe
            "false"  // Visit n'existe pas
    })
    void existsByStudentIdAndVisitDateTest(boolean exists) {
        // Given
        UUID studentId = randomUUID();
        LocalDate visitDate = LocalDate.now();
        given(visitRepositoryJPA.existsByStudentIdAndVisitDate(eq(studentId), eq(visitDate))).willReturn(exists);

        // When
        boolean result = visitRepositoryJPA.existsByStudentIdAndVisitDate(studentId, visitDate);

        // Then
        assertThat(result).isEqualTo(exists);
        verify(visitRepositoryJPA).existsByStudentIdAndVisitDate(eq(studentId), eq(visitDate));
    }

    @Test
    void saveTest() {
        // Given
        VisitEty visitEty = random(VisitEty.class);
        given(visitRepositoryJPA.save(any(VisitEty.class))).willReturn(visitEty);

        // When
        VisitEty savedVisit = visitRepositoryJPA.save(visitEty);

        // Then
        assertThat(savedVisit).isNotNull();
        assertThat(savedVisit.getId()).isEqualTo(visitEty.getId());
        verify(visitRepositoryJPA).save(any(VisitEty.class));
    }
}
