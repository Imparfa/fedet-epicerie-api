package fedet.epicerie.api.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class CardStorageService {
    @Value("${fedet.storage.cards.base-dir:/var/www/fedet/uploads/student_cards}")
    private String baseDir;

    @Value("${fedet.storage.cards.max-bytes:4194304}") // 2Mo
    private long maxBytes;

    public String save(String studentId, MultipartFile file, String logicalName) throws Exception {
        validate(file);
        Path dir = Paths.get(baseDir, studentId);
        Files.createDirectories(dir);
        String ext = ("image/png".equals(file.getContentType())) ? ".png" : ".jpg";
        String filename = logicalName + "_" + System.currentTimeMillis() + ext;
        Path target = dir.resolve(filename);
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return target.toAbsolutePath().toString();
    }

    public Resource asResource(String absolutePath) {
        if (absolutePath == null) return null;
        Path p = Paths.get(absolutePath);
        return new FileSystemResource(p);
    }

    private void validate(MultipartFile f) {
        if (f == null || f.isEmpty()) throw new IllegalArgumentException("Fichier manquant");
        String ct = f.getContentType();
        if (!"image/jpeg".equals(ct) && !"image/png".equals(ct)) throw new IllegalArgumentException("Format non autorisé");
        if (f.getSize() > maxBytes) throw new IllegalArgumentException("Fichier trop volumineux");
    }
}
