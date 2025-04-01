package fedet.epicerie.api.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "T_VISITS")
public class VisitEty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private StudentEty student;

    @Column(name = "VISIT_DATE")
    private LocalDate visitDate;

    @ManyToOne
    @JoinColumn(name = "DISTRIBUTION_ID", nullable = false)
    private DistributionEty distribution;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
}