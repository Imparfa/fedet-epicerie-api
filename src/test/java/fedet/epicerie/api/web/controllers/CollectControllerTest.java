package fedet.epicerie.api.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.models.Distribution;
import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.domain.ports.DistributionPort;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.web.dtos.ValidateCollectRequestDto;
import fedet.epicerie.api.web.mappers.StudentDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CollectControllerTest implements WithRandom {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentPort studentPort;

    @MockBean
    private DistributionPort distributionPort;

    @MockBean
    private VisitPort visitPort;

    @MockBean
    private StudentDtoMapper studentDtoMapper;

// TODO: Uncomment and repair this test

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testScanStudent_Success() throws Exception {
//        // Given
//        String qrCode = randomUUID().toString();
//        UUID studentId = randomUUID();
//        Student student = random(Student.class);
//        student.setId(studentId);
//        student.setFormation("ISEN");
//        student.setGraduation("BAC+5");
//        student.setQrCode(qrCode);
//        StudentDto studentDto = random(StudentDto.class);
//
//        when(studentPort.findById(eq(studentId))).thenReturn(student);
//        when(studentDtoMapper.toDto(eq(student))).thenReturn(studentDto);
//
//        // When & Then
//        mockMvc.perform(get("/collect/scan")
//                        .param("qrCode", qrCode)
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(studentDto)));
//
//        // Vérifier les mises à jour
//        verify(studentPort).updateLastVisitById(any(LocalDate.class), eq(student.getId()));
//        verify(studentPort).updateLastDistributionById(anyString(), eq(student.getId()));
//    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testScanStudent_NotFound() throws Exception {
        // Given
        String qrCode = randomUUID().toString();
        when(studentPort.findById(any(UUID.class))).thenReturn(null);

        // When & Then
        mockMvc.perform(get("/collect/scan")
                        .param("qrCode", qrCode)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testValidateCollect_Success() throws Exception {
        // Given
        ValidateCollectRequestDto requestDto = random(ValidateCollectRequestDto.class);
        UUID studentId = randomUUID();
        UUID distributionId = randomUUID();
        requestDto.setStudentId(studentId.toString());
        requestDto.setDistributionId(distributionId.toString());

        Student student = random(Student.class);
        student.setId(studentId);

        Distribution distribution = random(Distribution.class);
        student.setId(distributionId);

        Visit visit = random(Visit.class);
        visit.setStudent(student);
        visit.setDistribution(distribution);

        when(studentPort.findById(eq(studentId))).thenReturn(student);
        when(distributionPort.findById(eq(distributionId))).thenReturn(distribution);
        when(visitPort.hasVisitedToday(eq(student.getId()), eq(LocalDate.now()))).thenReturn(false);
        when(visitPort.save(any(Visit.class))).thenReturn(visit);

        // When & Then
        mockMvc.perform(post("/collect/validate")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testValidateCollect_AlreadyVisited() throws Exception {
        // Given
        ValidateCollectRequestDto requestDto = random(ValidateCollectRequestDto.class);
        UUID studentId = randomUUID();
        UUID distributionId = randomUUID();
        requestDto.setStudentId(studentId.toString());
        requestDto.setDistributionId(distributionId.toString());

        Student student = random(Student.class);
        student.setId(studentId);
        Distribution distribution = random(Distribution.class);
        distribution.setId(distributionId);

        when(studentPort.findById(eq(studentId))).thenReturn(student);
        when(distributionPort.findById(eq(distributionId))).thenReturn(distribution);
        when(visitPort.hasVisitedToday(eq(student.getId()), eq(LocalDate.now()))).thenReturn(true);

        // When & Then
        mockMvc.perform(post("/collect/validate")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void testScanStudent_Forbidden() throws Exception {
        String qrCode = randomString();

        mockMvc.perform(get("/collect/scan")
                        .param("qrCode", qrCode)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void testValidateCollect_Forbidden() throws Exception {
        ValidateCollectRequestDto requestDto = random(ValidateCollectRequestDto.class);
        requestDto.setStudentId(randomUUID().toString());

        mockMvc.perform(post("/collect/validate")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isForbidden());
    }
}
