package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.common.utils.WithRandom;
import fedet.epicerie.api.domain.models.Admin;
import fedet.epicerie.api.persistence.entities.AdminEty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AdminMapperTest implements WithRandom {

    private final AdminMapper adminMapper = Mappers.getMapper(AdminMapper.class);

    static Stream<AdminEty> adminEtyProvider() {
        return Stream.of(null, new AdminEty());  // null et instance valide instantiable
    }

    @ParameterizedTest
    @MethodSource("adminEtyProvider")
    void toModelTest(AdminEty adminEty) {
        // When
        Admin admin = adminMapper.toModel(adminEty);

        // Then
        if (adminEty == null) {
            assertThat(admin).isNull();  // Cas où l'entrée est null
        } else {
            assertThat(admin).isNotNull();
            assertThat(admin.getId()).isEqualTo(adminEty.getId());
            assertThat(admin.getFirstname()).isEqualTo(adminEty.getFirstname());
            assertThat(admin.getLastname()).isEqualTo(adminEty.getLastname());
            assertThat(admin.getEmail()).isEqualTo(adminEty.getEmail());
            assertThat(admin.getPassword()).isEqualTo(adminEty.getPassword());
            assertThat(admin.getRole()).isEqualTo(adminEty.getRole());
        }
    }

    static Stream<Admin> adminProvider() {
        return Stream.of(null, Admin.builder().build());  // null et instance valide via builder
    }

    @ParameterizedTest
    @MethodSource("adminProvider")
    void toEntityTest(Admin admin) {
        // When
        AdminEty adminEty = adminMapper.toEntity(admin);

        // Then
        if (admin == null) {
            assertThat(adminEty).isNull();  // Cas où l'entrée est null
        } else {
            assertThat(adminEty).isNotNull();
            assertThat(adminEty.getId()).isEqualTo(admin.getId());
            assertThat(adminEty.getFirstname()).isEqualTo(admin.getFirstname());
            assertThat(adminEty.getLastname()).isEqualTo(admin.getLastname());
            assertThat(adminEty.getEmail()).isEqualTo(admin.getEmail());
            assertThat(adminEty.getPassword()).isEqualTo(admin.getPassword());
            assertThat(adminEty.getRole()).isEqualTo(admin.getRole());
        }
    }
}
