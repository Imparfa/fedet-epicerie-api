package fedet.epicerie.api.domain.services;

import fedet.epicerie.api.common.utils.WithRandom;

//@ExtendWith(MockitoExtension.class)
class QrCodeServiceTest implements WithRandom {
//    @InjectMocks
//    private QrCodeService qrCodeService;
//
//    @Mock
//    private QRCodeWriter mockQrCodeWriter;
//
//    @BeforeEach
//    void setUp() {
//        // Utiliser ReflectionTestUtils pour remplacer le QRCodeWriter par le mock
//        ReflectionTestUtils.setField(qrCodeService, "qrCodeWriter", mockQrCodeWriter);
//    }
//
//    @Test
//    void testGenerateQrCode_ValidInput() throws WriterException {
//        // Given
//        String content = randomUUID().toString();
//        BitMatrix mockBitMatrix = new BitMatrix(300, 300);
//        when(mockQrCodeWriter.encode(anyString(), any(), anyInt(), anyInt(), anyMap()))
//                .thenReturn(mockBitMatrix);
//
//        // When
//        String qrCodeBase64 = qrCodeService.generateQrCode(content);
//
//        // Then
//        assertThat(qrCodeBase64).isNotNull();
//        assertThat(qrCodeBase64).startsWith("data:image/png;base64,");
//    }
//
//    @Test
//    void testGenerateQrCode_EmptyInput() {
//        // Given
//        String content = "";
//
//        // When & Then
//        assertThrows(IllegalArgumentException.class, () -> qrCodeService.generateQrCode(content));
//    }
//
//    @Test
//    void testGenerateQrCode_NullInput() {
//        // Given, When & Then
//        assertThrows(IllegalArgumentException.class, () -> qrCodeService.generateQrCode(null));
//    }
//
//    @Test
//    void testGenerateQrCode_WriterException() throws WriterException {
//        // Given
//        String content = "Invalid Content";
//        when(mockQrCodeWriter.encode(anyString(), any(), anyInt(), anyInt(), anyMap()))
//                .thenThrow(new WriterException("Forced WriterException for testing"));
//
//        // When & Then
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> qrCodeService.generateQrCode(content));
//        assertThat(exception.getMessage()).isEqualTo("Error while generating QR code.");
//        assertThat(exception.getCause()).isInstanceOf(WriterException.class);
//        assertThat(exception.getCause().getMessage()).isEqualTo("Forced WriterException for testing");
//    }
}
