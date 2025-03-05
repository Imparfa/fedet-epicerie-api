package fedet.epicerie.api.web.config;

//@WebMvcTest(SecurityConfig.class)
//@AutoConfigureMockMvc
public class SecurityConfigTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private JwtAuthenticationFilter jwtAuthenticationFilter;  // ✅ Mock complet pour éviter les erreurs de filtre
//
//    private final String fakeJwtToken = "Bearer fake-jwt-token";
//
//    // ✅ Moquer correctement doFilter avec doAnswer pour ignorer le filtre réel
//    @BeforeEach
//    void setUp() throws Exception {
//        doAnswer(invocation -> null).when(jwtAuthenticationFilter).doFilter(
//                Mockito.any(HttpServletRequest.class),
//                Mockito.any(HttpServletResponse.class),
//                Mockito.any(FilterChain.class)
//        );
//    }
//
//    @Test
//    void shouldAllowAccessToPublicEndpoints() throws Exception {
//        String publicEndpoint = "/auth/login";
//        mockMvc.perform(MockMvcRequestBuilders.get(publicEndpoint)
//                        .header(HttpHeaders.AUTHORIZATION, fakeJwtToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(roles = {"STUDENT"})
//    void shouldAllowAccessToStudentEndpointsForStudentRole() throws Exception {
//        String studentEndpoint = "/student/profile";
//        mockMvc.perform(MockMvcRequestBuilders.get(studentEndpoint)
//                        .header(HttpHeaders.AUTHORIZATION, fakeJwtToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    void shouldAllowAccessToStudentEndpointsForAdminRole() throws Exception {
//        String studentEndpoint = "/student/profile";
//        mockMvc.perform(MockMvcRequestBuilders.get(studentEndpoint)
//                        .header(HttpHeaders.AUTHORIZATION, fakeJwtToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(roles = {"STUDENT"})
//    void shouldDenyAccessToManagementEndpointsForStudentRole() throws Exception {
//        String managementEndpoint = "/management/dashboard";
//        mockMvc.perform(MockMvcRequestBuilders.get(managementEndpoint)
//                        .header(HttpHeaders.AUTHORIZATION, fakeJwtToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    void shouldAllowAccessToManagementEndpointsForAdminRole() throws Exception {
//        String managementEndpoint = "/management/dashboard";
//        mockMvc.perform(MockMvcRequestBuilders.get(managementEndpoint)
//                        .header(HttpHeaders.AUTHORIZATION, fakeJwtToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}
