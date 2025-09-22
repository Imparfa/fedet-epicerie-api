package fedet.epicerie.api.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.ports.AdminPort;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.services.QrCodeService;
import fedet.epicerie.api.web.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest implements WithRandom {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AdminPort adminPort;

    @Autowired
    private StudentPort studentPort;

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private JwtService jwtService;
// TODO: Uncomment and repair this test
//
//    @Test
//    @WithMockUser
//    void testRegisterStudent_Success() throws Exception {
//        RegisterRequestDto registerRequestDto = random(RegisterRequestDto.class);
//        Student student = random(Student.class);
//        student.setEmail(registerRequestDto.getEmail());
//        student.setPassword(registerRequestDto.getPassword());
//        String token = randomString();
//
//        when(studentPort.save(any())).thenReturn(student);
//        when(qrCodeService.generateQrCode(any())).thenReturn("dummy-qr-code");
//        when(jwtService.generateTokenWithRole(student.getEmail(), "STUDENT")).thenReturn(token);
//
//        AuthResponseDto expectedResponse = new AuthResponseDto().token(token).role(RoleDto.STUDENT);
//
//        mockMvc.perform(post("/auth/register")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(registerRequestDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
//    }
//
//    @Test
//    @WithMockUser
//    void testLoginStudent_Success() throws Exception {
//        LoginRequestDto loginRequestDto = random(LoginRequestDto.class);
//        Student student = random(Student.class);
//        student.setEmail(loginRequestDto.getEmail());
//        student.setPassword(loginRequestDto.getPassword());
//        String token = randomString();
//
//        when(studentPort.findByEmail(loginRequestDto.getEmail())).thenReturn(student);
//        when(jwtService.generateTokenWithRole(student.getEmail(), "STUDENT")).thenReturn(token);
//
//        AuthResponseDto expectedResponse = new AuthResponseDto().token(token).role(RoleDto.STUDENT);
//
//        mockMvc.perform(post("/auth/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequestDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
//    }
//
//    @Test
//    @WithMockUser
//    void testLogin_InvalidCredentials() throws Exception {
//        LoginRequestDto loginRequestDto = random(LoginRequestDto.class);
//
//        when(adminPort.findByEmail(loginRequestDto.getEmail())).thenReturn(null);
//        when(studentPort.findByEmail(loginRequestDto.getEmail())).thenReturn(null);
//
//        mockMvc.perform(post("/auth/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequestDto)))
//                .andExpect(status().isUnauthorized());
//    }
}
