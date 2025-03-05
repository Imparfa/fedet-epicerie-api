package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.web.dtos.FormationDto;
import fedet.epicerie.api.web.dtos.StudentDto;
import fedet.epicerie.api.web.dtos.StudentEditRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentDtoMapperTest implements WithRandom {

    @Mock
    private GraduationDtoMapper graduationDtoMapper;

    @InjectMocks
    private StudentDtoMapper studentDtoMapper = Mappers.getMapper(StudentDtoMapper.class);  // ✅ Injecte les mocks

    static Stream<Object[]> toDtoProvider() {
        // Fournisseur de paramètres pour le test toDto avec des données aléatoires
        Student randomStudent = Student.builder()
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(2000, 1, 1))
                .email("john.doe@example.com")
                .formation("MMI")
                .build();

        StudentDto expectedDto = new StudentDto();
        expectedDto.setFirstname(randomStudent.getFirstname());
        expectedDto.setLastname(randomStudent.getLastname());
        expectedDto.setBirthdate(randomStudent.getBirthdate());
        expectedDto.setEmail(randomStudent.getEmail());
        expectedDto.setFormation(FormationDto.MMI);

        return Stream.of(
                new Object[]{null, null},  // Cas null
                new Object[]{randomStudent, expectedDto}  // Cas valide
        );
    }

    @ParameterizedTest
    @MethodSource("toDtoProvider")
    void testToDto(Student student, StudentDto expectedDto) {
        // When
        StudentDto actualDto = studentDtoMapper.toDto(student);

        // Then
        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    void testUpdateStudentFromDto() {
        // Given
        Student student = Student.builder()
                .id(UUID.randomUUID())
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(2000, 1, 1))
                .email("john.doe@example.com")
                .formation("MMI")
                .build();

        StudentEditRequestDto dto = new StudentEditRequestDto();
        dto.setFirstname("Jane");
        dto.setBirthdate(LocalDate.of(1999, 12, 31));
        dto.setEmail("jane.doe@example.com");
        dto.setFormation(FormationDto.ISEN);

        // When
        studentDtoMapper.updateStudentFromDto(student, dto, false);

        // Then
        assertThat(student.getFirstname()).isEqualTo("Jane");
        assertThat(student.getLastname()).isEqualTo("Doe");
        assertThat(student.getBirthdate()).isEqualTo(LocalDate.of(1999, 12, 31));
        assertThat(student.getEmail()).isEqualTo("jane.doe@example.com");
        assertThat(student.getFormation()).isEqualTo("ISEN");
    }

    @Test
    void testUpdateStudentFromDto_NullDto() {
        // Given
        Student student = Student.builder()
                .id(UUID.randomUUID())
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(2000, 1, 1))
                .email("john.doe@example.com")
                .formation("MMI")
                .build();

        // When
        studentDtoMapper.updateStudentFromDto(student, null, false);

        // Then
        assertThat(student.getFirstname()).isEqualTo("John");
        assertThat(student.getLastname()).isEqualTo("Doe");
        assertThat(student.getBirthdate()).isEqualTo(LocalDate.of(2000, 1, 1));
        assertThat(student.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(student.getFormation()).isEqualTo("MMI");
    }
}
