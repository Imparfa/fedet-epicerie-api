package fedet.epicerie.api.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "T_STUDENTS")
public class StudentEty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "BIRTHDATE")
    private LocalDate birthdate;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FORMATION")
    private String formation;

    @Column(name = "GRADUATION")
    private String graduation;

    @Column(name = "IS_STUDENT")
    private Boolean isStudent;

    @Column(name = "IS_WORKER")
    private Boolean isWorker;

    @Column(name = "HOUSEHOLD")
    private Integer household;

    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "LAST_VISIT")
    private LocalDate lastVisit;

    @Column(name = "LAST_LOCATION")
    private String lastLocation;

    @Column(name = "QR_CODE", columnDefinition = "TEXT")
    private String qrCode;
}