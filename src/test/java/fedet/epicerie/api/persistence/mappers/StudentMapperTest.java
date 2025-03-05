package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.persistence.entities.StudentEty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StudentMapperTest implements WithRandom {

    private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    static Stream<StudentEty> studentEtyProvider() {
        return Stream.of(null, new StudentEty());  // null et instance valide instantiable
    }

    @ParameterizedTest
    @MethodSource("studentEtyProvider")
    void toModelTest(StudentEty studentEty) {
        // When
        Student student = studentMapper.toModel(studentEty);

        // Then
        if (studentEty == null) {
            assertThat(student).isNull();  // Cas où l'entrée est null
        } else {
            assertThat(student).isNotNull();
            assertThat(student.getId()).isEqualTo(studentEty.getId());
            assertThat(student.getFirstname()).isEqualTo(studentEty.getFirstname());
            assertThat(student.getLastname()).isEqualTo(studentEty.getLastname());
            assertThat(student.getEmail()).isEqualTo(studentEty.getEmail());
        }
    }

    static Stream<Student> studentProvider() {
        return Stream.of(null, Student.builder().build());  // null et instance valide via builder
    }

    @ParameterizedTest
    @MethodSource("studentProvider")
    void toEntityTest(Student student) {
        // When
        StudentEty studentEty = studentMapper.toEntity(student);

        // Then
        if (student == null) {
            assertThat(studentEty).isNull();  // Cas où l'entrée est null
        } else {
            assertThat(studentEty).isNotNull();
            assertThat(studentEty.getId()).isEqualTo(student.getId());
            assertThat(studentEty.getFirstname()).isEqualTo(student.getFirstname());
            assertThat(studentEty.getLastname()).isEqualTo(student.getLastname());
            assertThat(studentEty.getEmail()).isEqualTo(student.getEmail());
        }
    }
}
