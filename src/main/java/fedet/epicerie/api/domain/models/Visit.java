package fedet.epicerie.api.domain.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Visit {
    private Integer id;
    private Student student;
    private LocalDate visitDate;
    private String location;
    private String paymentMethod;
}
