package fedet.epicerie.api.web.mappers;

import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.web.dtos.StudentDto;
import fedet.epicerie.api.web.dtos.StudentEditRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GraduationDtoMapper.class, FormationDtoMapper.class})
public interface StudentDtoMapper {

    StudentDto toDto(Student student);

    default void updateStudentFromDto(Student student, StudentEditRequestDto studentEditRequestDto, boolean isAdmin) {
        if (student == null || studentEditRequestDto == null) {
            return;
        }
        if (studentEditRequestDto.getFirstname() != null) student.setFirstname(studentEditRequestDto.getFirstname());
        if (studentEditRequestDto.getLastname() != null) student.setLastname(studentEditRequestDto.getLastname());
        if (studentEditRequestDto.getBirthdate() != null) student.setBirthdate(studentEditRequestDto.getBirthdate());
        if (studentEditRequestDto.getEmail() != null) student.setEmail(studentEditRequestDto.getEmail());
        if (studentEditRequestDto.getFormation() != null)
            student.setFormation(studentEditRequestDto.getFormation().getValue());
        if (studentEditRequestDto.getGraduation() != null)
            student.setGraduation(studentEditRequestDto.getGraduation().getValue());

        if (isAdmin) {
            if (studentEditRequestDto.getIsStudent() != null)
                student.setIsStudent(studentEditRequestDto.getIsStudent());
            if (studentEditRequestDto.getIsWorker() != null) student.setIsWorker(studentEditRequestDto.getIsWorker());
            if (studentEditRequestDto.getHousehold() != null)
                student.setHousehold(studentEditRequestDto.getHousehold());
        }
    }
}
