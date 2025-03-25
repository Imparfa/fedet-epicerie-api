package fedet.epicerie.api.web.controller;

import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.web.apis.StudentApi;
import fedet.epicerie.api.web.dtos.StudentDto;
import fedet.epicerie.api.web.dtos.StudentEditRequestDto;
import fedet.epicerie.api.web.mappers.StudentDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
public class StudentController implements StudentApi {
    private final StudentPort studentPort;
    private final StudentDtoMapper studentDtoMapper;

    @Override
    public ResponseEntity<StudentDto> getProfile(@RequestParam @Nullable String email) {
        String targetEmail = isAdmin() && email != null ? email : getEmailFromBearer();

        Student existingStudent = studentPort.findByEmail(targetEmail);
        if (existingStudent == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(studentDtoMapper.toDto(existingStudent));
    }

    @Override
    public ResponseEntity<StudentDto> editProfile(@RequestBody StudentEditRequestDto studentEditRequestDto, @RequestParam @Nullable String email) {
        String targetEmail = isAdmin() && email != null ? email : getEmailFromBearer();

        Student existingStudent = studentPort.findByEmail(targetEmail);
        if (existingStudent == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        studentDtoMapper.updateStudentFromDto(existingStudent, studentEditRequestDto, isAdmin());
        Student updatedStudent = studentPort.editStudent(existingStudent);

        return ResponseEntity.ok(studentDtoMapper.toDto(updatedStudent));
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String getEmailFromBearer() {
        return getAuthentication().getName();
    }

    private boolean isAdmin() {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }
}