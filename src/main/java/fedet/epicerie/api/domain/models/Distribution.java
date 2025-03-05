package fedet.epicerie.api.domain.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Distribution {
    private UUID id;
    private String name;
    private String address;
    private Boolean isActive;
}
