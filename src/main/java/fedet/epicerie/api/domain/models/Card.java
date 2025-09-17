package fedet.epicerie.api.domain.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Card {
    private UUID id;
    private Student student;
    private String frontPath;
    private String backPath;
    private String status;
    private LocalDate validityDate;
    private UUID validatedBy;
    private LocalDate uploadedAt;
}
