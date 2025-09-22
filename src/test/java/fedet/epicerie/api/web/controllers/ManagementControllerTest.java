package fedet.epicerie.api.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.domain.ports.VisitPort;
import fedet.epicerie.api.web.mappers.StudentDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ManagementControllerTest implements WithRandom {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentPort studentPort;

    @Autowired
    private VisitPort visitPort;

    @Autowired
    private StudentDtoMapper studentDtoMapper;

// TODO: Uncomment and implement the test
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testGetStudents_Success() throws Exception {
//        // Given
//        // Créer un Student et un StudentDto avec les mêmes valeurs
//        String email = randomString();
//        LocalDate birthdate = LocalDate.of(2000, 1, 1);
//
//        StudentDto exampleDto = new StudentDto()
//                .formation(FormationDto.ISEN)
//                .graduation(GraduationDto.BAC_5)
//                .birthdate(birthdate)
//                .email(email)
//                .firstname("John")
//                .lastname("Doe")
//                .isStudent(true)
//                .isWorker(false)
//                .household(null);
//
//        Student exampleModel = Student.builder()
//                .formation("ISEN")
//                .graduation("BAC+5")
//                .birthdate(birthdate)
//                .email(email)
//                .firstname("John")
//                .lastname("Doe")
//                .isStudent(true)
//                .isWorker(false)
//                .household(null)
//                .build();
//
//        List<Student> students = List.of(exampleModel);
//        List<StudentDto> studentDtos = List.of(exampleDto);
//
//        // Mock correct du StudentDtoMapper pour chaque étudiant
//        when(studentPort.findAll()).thenReturn(students);
//        when(studentDtoMapper.toDto(any(Student.class))).thenAnswer(invocation -> {
//            Student student = invocation.getArgument(0);
//            if (student.getEmail().equals(email)) {
//                return exampleDto;
//            }
//            return null;
//        });
//
//        // When & Then
//        mockMvc.perform(get("/management/students")
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(studentDtos)));
//    }

//
//    @Test
//    @WithMockUser(roles = "STUDENT")
//    void testGetStudents_Forbidden() throws Exception {
//        // When & Then
//        mockMvc.perform(get("/management/students")
//                        .with(csrf()))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testGetStats_Success() throws Exception {
//        // Given
//        List<Student> students = List.of(random(Student.class));
//        List<Visit> visits = List.of(random(Visit.class));
//        List<Visit> todayVisits = visits.stream()
//                .filter(visit -> visit.getVisitDate().isEqual(LocalDate.now()))
//                .collect(Collectors.toList());
//
//        when(studentPort.findAll()).thenReturn(students);
//        when(visitPort.findAll()).thenReturn(visits);
//        when(visitPort.findByDate(LocalDate.now())).thenReturn(todayVisits);
//
//        StatsResponseDto expectedStats = new StatsResponseDto()
//                .totalStudents(students.size())
//                .totalVisits(visits.size())
//                .visitsToday(todayVisits.size());
//
//        // When & Then
//        mockMvc.perform(get("/management/stats")
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(expectedStats)));
//    }

//    @Test
//    @WithMockUser(roles = "STUDENT")
//    void testGetStats_Forbidden() throws Exception {
//        // When & Then
//        mockMvc.perform(get("/management/stats")
//                        .with(csrf()))
//                .andExpect(status().isForbidden());
//    }
}
