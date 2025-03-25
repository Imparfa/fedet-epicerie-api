package fedet.epicerie.api.web.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class VaultService {

    @Value("${value:superSecretKeyForJwtGenerationWithAtLeast256Bits}")
    private String secretKey;

}
