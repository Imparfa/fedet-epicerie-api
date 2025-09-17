package fedet.epicerie.api.web.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;



@Getter
@Setter
@ConfigurationProperties(prefix = "cards.storage")
class CardStorageProperties {

    private Path baseDir = Paths.get("/var/www/fedet/uploads/student_cards");

    /** taille max 2 Mo */
    private long maxBytes = 2 * 1024 * 1024;

    private Set<String> allowedContentTypes = Set.of("image/jpeg", "image/png");
}

@Configuration
@EnableConfigurationProperties(CardStorageProperties.class)
public class CardStorageConfig {
}