package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.domain.models.Student;
import fedet.epicerie.api.domain.ports.StudentPort;
import fedet.epicerie.api.persistence.mappers.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StudentRepository implements StudentPort {
    private final StudentRepositoryJPA studentRepositoryJPA;
    private final StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentRepositoryJPA.findAll().stream()
                .map(studentMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Student findById(UUID id) {
        return studentRepositoryJPA.findById(id)
                .map(studentMapper::toModel)
                .orElse(null);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepositoryJPA.findByEmail(email)
                .map(studentMapper::toModel)
                .orElse(null);
    }

    @Override
    public Student save(Student student) {
        return studentMapper.toModel(studentRepositoryJPA.save(studentMapper.toEntity(student)));
    }

    @Override
    public Student editStudent(Student student) {
        return studentMapper.toModel(studentRepositoryJPA.save(studentMapper.toEntity(student)));
    }

    @Override
    public void updateLastVisitById(LocalDate lastVisit, UUID id) {
        studentRepositoryJPA.updateLastVisitById(lastVisit, id);
    }

    @Override
    public void updateLastLocationById(String lastLocation, UUID id) {
        studentRepositoryJPA.updateLastLocationById(lastLocation, id);
    }

    @Override
    public void updateQRCodeById(String qrCode, UUID id) {
        studentRepositoryJPA.updateQRCodeById(qrCode, id);
    }
}