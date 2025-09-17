package fedet.epicerie.api.persistence.entities;

import fedet.epicerie.api.domain.models.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "T_CARDS")
public class CardEty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private StudentEty student;

    @Column(name="FRONT_PATH", nullable = false)
    private String frontPath;

    @Column(name="BACK_PATH")
    private String backPath;

    @Column(name="STATUS", nullable = false)
    private String status;

    /** Date de fin de validité de la carte réelle (si connue) */
    @Column(name = "VALIDITY_DATE")
    private LocalDate validityDate;

    /** Qui a validé (id d’un admin – ou User), pour traçabilité */
    @Column(name = "VALIDATED_BY")
    private UUID validatedBy;

    @Column(name = "UPLOADED_AT", nullable = false)
    private LocalDate uploadedAt;
}
