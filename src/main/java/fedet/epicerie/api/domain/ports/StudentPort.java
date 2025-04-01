package fedet.epicerie.api.domain.ports;

import fedet.epicerie.api.domain.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StudentPort {
    List<Student> findAll();

    Page<Student> findAll(Pageable pageable);

    Student findById(UUID id);

    Student findByEmail(String email);

    Page<Student> searchByNameOrEmail(String keyword, Pageable pageable);

    Student save(Student student);

    Student editStudent(Student student);

    void updateLastVisitById(LocalDate lastVisit, UUID id);

    void updateLastDistributionById(String lastDistribution, UUID id);

    void updateQRCodeById(String qrCode, UUID id);
}
