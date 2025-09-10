package fedet.epicerie.api.domain.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Visit {
    private UUID id;
    private Student student;
    private Distribution distribution;
    private LocalDate visitDate;
    private String paymentMethod;
}
