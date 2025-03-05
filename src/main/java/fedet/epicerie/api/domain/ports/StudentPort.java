package fedet.epicerie.api.domain.ports;

import fedet.epicerie.api.domain.models.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StudentPort {
    List<Student> findAll();

    Student findById(UUID id);

    Student findByEmail(String email);

    Student save(Student student);

    Student editStudent(Student student);

    void updateLastVisitById(LocalDate lastVisit, UUID id);

    void updateLastLocationById(String lastLocation, UUID id);

    void updateQRCodeById(String qrCode, UUID id);
}
