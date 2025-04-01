package fedet.epicerie.api.web.controller;

import fedet.epicerie.api.domain.models.Admin;
import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.ports.AdminPort;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.services.QrCodeService;
import fedet.epicerie.api.web.apis.AuthenticationApi;
import fedet.epicerie.api.web.dtos.AuthResponseDto;
import fedet.epicerie.api.web.dtos.LoginRequestDto;
import fedet.epicerie.api.web.dtos.RegisterRequestDto;
import fedet.epicerie.api.web.dtos.RoleDto;
import fedet.epicerie.api.web.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class AuthenticationController implements AuthenticationApi {
    private final AdminPort adminPort;
    private final StudentPort studentPort;
    private final QrCodeService qrCodeService;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<AuthResponseDto> registerStudent(@RequestBody RegisterRequestDto registerRequestDto) {
        Student student = Student.builder()
                .firstname(registerRequestDto.getFirstname())
                .lastname(registerRequestDto.getLastname())
                .email(registerRequestDto.getEmail())
                .birthdate(registerRequestDto.getBirthdate())
                .formation(registerRequestDto.getFormation() != null ? registerRequestDto.getFormation().getValue() : "")
                .graduation(registerRequestDto.getGraduation() != null ? registerRequestDto.getGraduation().getValue() : "")
                .createdAt(LocalDate.now())
                .isStudent(registerRequestDto.getIsStudent())
                .isWorker(registerRequestDto.getIsWorker())
                .household(registerRequestDto.getHousehold())
                .password(registerRequestDto.getPassword())
                .build();

        student = studentPort.save(student);

        studentPort.updateQRCodeById(qrCodeService.generateQrCode(student.getId().toString()), student.getId());

        String token = jwtService.generateTokenWithRole(student.getEmail(), "STUDENT");

        AuthResponseDto response = new AuthResponseDto().token(token).role(RoleDto.STUDENT);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AuthResponseDto> loginStudentAndAdmin(@RequestBody LoginRequestDto loginRequestDto) {
        Admin admin = adminPort.findByEmail(loginRequestDto.getEmail());
        if (admin != null && admin.getPassword().equals(loginRequestDto.getPassword())) {
            String token = jwtService.generateTokenWithRole(admin.getEmail(), "ADMIN");
            return ResponseEntity.ok(new AuthResponseDto().token(token).role(RoleDto.ADMIN));
        }

        Student student = studentPort.findByEmail(loginRequestDto.getEmail());
        if (student != null && student.getPassword().equals(loginRequestDto.getPassword())) {
            String token = jwtService.generateTokenWithRole(student.getEmail(), "STUDENT");
            return ResponseEntity.ok(new AuthResponseDto().token(token).role(RoleDto.STUDENT));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}