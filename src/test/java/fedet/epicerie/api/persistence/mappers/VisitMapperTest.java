package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.models.Distribution;
import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.models.Visit;
import fedet.epicerie.api.persistence.entities.DistributionEty;
import fedet.epicerie.api.persistence.entities.StudentEty;
import fedet.epicerie.api.persistence.entities.VisitEty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class VisitMapperTest implements WithRandom {

    private final VisitMapper visitMapper = Mappers.getMapper(VisitMapper.class);

    static Stream<VisitEty> visitEtyProvider() {
        VisitEty studentFilled = new VisitEty();
        studentFilled.setStudent(new StudentEty());
        studentFilled.setDistribution(new DistributionEty());
        return Stream.of(null, new VisitEty(), studentFilled);  // null et instance valide instantiable
    }

    @ParameterizedTest
    @MethodSource("visitEtyProvider")
    void toModelTest(VisitEty visitEty) {
        // When
        Visit visit = visitMapper.toModel(visitEty);

        // Then
        if (visitEty == null) {
            assertThat(visit).isNull();  // Cas où l'entrée est null
        } else {
            assertThat(visit).isNotNull();
            assertThat(visit.getId()).isEqualTo(visitEty.getId());
            assertThat(visit.getPaymentMethod()).isEqualTo(visitEty.getPaymentMethod());
        }
    }

    static Stream<Visit> visitProvider() {
        return Stream.of(null, Visit.builder().build(), Visit.builder().student(Student.builder().build()).distribution(Distribution.builder().build()).build());  // null et instance valide via builder
    }

    @ParameterizedTest
    @MethodSource("visitProvider")
    void toEntityTest(Visit visit) {
        // When
        VisitEty visitEty = visitMapper.toEntity(visit);

        // Then
        if (visit == null) {
            assertThat(visitEty).isNull();  // Cas où l'entrée est null
        } else {
            assertThat(visitEty).isNotNull();
            assertThat(visitEty.getId()).isEqualTo(visit.getId());
            assertThat(visitEty.getPaymentMethod()).isEqualTo(visit.getPaymentMethod());
        }
    }
}
