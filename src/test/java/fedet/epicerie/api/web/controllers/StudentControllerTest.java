package fedet.epicerie.api.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.web.mappers.StudentDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest implements WithRandom {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentPort studentPort;

    @Autowired
    private StudentDtoMapper studentDtoMapper;

//    @Test
//    @WithMockUser(username = "test@student.com", roles = "STUDENT")
//    void testGetProfile_Success() throws Exception {
//        // Given
//        Student student = random(Student.class);
//        student.setEmail("test@student.com");
//        StudentDto studentDto = random(StudentDto.class);
//
//        when(studentPort.findByEmail("test@student.com")).thenReturn(student);
//        when(studentDtoMapper.toDto(student)).thenReturn(studentDto);
//
//        // When & Then
//        mockMvc.perform(get("/student/profile")
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(studentDto)));
//    }
//
//    @Test
//    @WithMockUser(username = "test@student.com", roles = "STUDENT")
//    void testGetProfile_NotFound() throws Exception {
//        // Given
//        when(studentPort.findByEmail("test@student.com")).thenReturn(null);
//
//        // When & Then
//        mockMvc.perform(get("/student/profile")
//                        .with(csrf()))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @WithMockUser(username = "test@student.com", roles = "STUDENT")
//    void testEditProfile_Success() throws Exception {
//        // Given
//        Student student = random(Student.class);
//        student.setEmail("test@student.com");
//        StudentDto updatedStudentDto = random(StudentDto.class);
//        StudentEditRequestDto editRequestDto = random(StudentEditRequestDto.class);
//
//        when(studentPort.findByEmail("test@student.com")).thenReturn(student);
//        when(studentDtoMapper.toDto(student)).thenReturn(updatedStudentDto);
//        when(studentPort.editStudent(any())).thenReturn(student);
//
//        // When & Then
//        mockMvc.perform(patch("/student/profile")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(editRequestDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(updatedStudentDto)));
//    }

//    @Test
//    @WithMockUser(username = "test@student.com", roles = "STUDENT")
//    void testEditProfile_InvalidInput() throws Exception {
//        // Given
//        StudentEditRequestDto invalidRequestDto = new StudentEditRequestDto();  // Champs manquants
//
//        // When & Then
//        mockMvc.perform(patch("/student/profile")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(invalidRequestDto)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @WithMockUser(username = "admin@example.com", roles = "UNDEFINED")
//    void testEditProfile_Forbidden() throws Exception {
//        // Given
//        StudentEditRequestDto editRequestDto = random(StudentEditRequestDto.class);
//
//        // When & Then
//        mockMvc.perform(patch("/student/profile")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(editRequestDto)))
//                .andExpect(status().isForbidden());
//    }
}
