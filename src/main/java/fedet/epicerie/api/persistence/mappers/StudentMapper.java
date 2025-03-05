package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.persistence.entities.StudentEty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentEty toEntity(Student student);

    Student toModel(StudentEty ety);
}
