package fedet.epicerie.api.domain.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Student {
    private UUID id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String formation;
    private String graduation;
    private Boolean isStudent;
    private Boolean isWorker;
    private Integer household;
    private LocalDate createdAt;
    private LocalDate lastVisit;
    private String lastLocation;
    private String qrCode;
}
