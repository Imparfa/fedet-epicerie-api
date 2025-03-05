package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.persistence.entities.StudentEty;
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
class StudentRepositoryJPATest implements WithRandom {

    @Mock
    private StudentRepositoryJPA studentRepositoryJPA;

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void findByIdTest(boolean exists) {
        // Given
        UUID id = randomUUID();
        StudentEty expectedStudent = exists ? random(StudentEty.class) : null;
        if (exists) expectedStudent.setId(id);
        given(studentRepositoryJPA.findById(eq(id))).willReturn(Optional.ofNullable(expectedStudent));

        // When
        Optional<StudentEty> result = studentRepositoryJPA.findById(id);

        // Then
        if (exists) {
            assertThat(result).isPresent();
            assertThat(result.get().getId()).isEqualTo(id);
        } else {
            assertThat(result).isEmpty();
        }
        verify(studentRepositoryJPA).findById(eq(id));
    }

    @Test
    void saveTest() {
        // Given
        StudentEty studentEty = random(StudentEty.class);
        given(studentRepositoryJPA.save(any(StudentEty.class))).willReturn(studentEty);

        // When
        StudentEty savedStudent = studentRepositoryJPA.save(studentEty);

        // Then
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isEqualTo(studentEty.getId());
        verify(studentRepositoryJPA).save(any(StudentEty.class));
    }
}
