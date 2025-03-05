package fedet.epicerie.api.domain.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class QrCodeService {
    public String generateQrCode(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("QR code content cannot be null or empty.");
        }
        try {

            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(image, "PNG", outputStream);

            String base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            return "data:image/png;base64," + base64Image;
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Erreur lors de la génération du QR Code", e);
        }
    }
}