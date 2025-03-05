package fedet.epicerie.api.domain.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Admin {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
