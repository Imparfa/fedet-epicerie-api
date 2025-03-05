package fedet.epicerie.api.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.ports.AdminPort;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.services.QrCodeService;
import fedet.epicerie.api.web.dtos.AuthResponseDto;
import fedet.epicerie.api.web.dtos.LoginRequestDto;
import fedet.epicerie.api.web.dtos.RegisterRequestDto;
import fedet.epicerie.api.web.dtos.RoleDto;
import fedet.epicerie.api.web.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest implements WithRandom {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdminPort adminPort;

    @MockBean
    private StudentPort studentPort;

    @MockBean
    private QrCodeService qrCodeService;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser
    void testRegisterStudent_Success() throws Exception {
        RegisterRequestDto registerRequestDto = random(RegisterRequestDto.class);
        Student student = random(Student.class);
        student.setEmail(registerRequestDto.getEmail());
        student.setPassword(registerRequestDto.getPassword());
        String token = randomString();

        when(studentPort.save(any())).thenReturn(student);
        when(qrCodeService.generateQrCode(any())).thenReturn("dummy-qr-code");
        when(jwtService.generateTokenWithRole(student.getEmail(), "STUDENT")).thenReturn(token);

        AuthResponseDto expectedResponse = new AuthResponseDto().token(token).role(RoleDto.STUDENT);

        mockMvc.perform(post("/auth/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    @WithMockUser
    void testLoginStudent_Success() throws Exception {
        LoginRequestDto loginRequestDto = random(LoginRequestDto.class);
        Student student = random(Student.class);
        student.setEmail(loginRequestDto.getEmail());
        student.setPassword(loginRequestDto.getPassword());
        String token = randomString();

        when(studentPort.findByEmail(loginRequestDto.getEmail())).thenReturn(student);
        when(jwtService.generateTokenWithRole(student.getEmail(), "STUDENT")).thenReturn(token);

        AuthResponseDto expectedResponse = new AuthResponseDto().token(token).role(RoleDto.STUDENT);

        mockMvc.perform(post("/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    @WithMockUser
    void testLogin_InvalidCredentials() throws Exception {
        LoginRequestDto loginRequestDto = random(LoginRequestDto.class);

        when(adminPort.findByEmail(loginRequestDto.getEmail())).thenReturn(null);
        when(studentPort.findByEmail(loginRequestDto.getEmail())).thenReturn(null);

        mockMvc.perform(post("/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andExpect(status().isUnauthorized());
    }
}
