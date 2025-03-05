package fedet.epicerie.api.persistence.repositories;

import fedet.epicerie.api.domain.models.Admin;
import fedet.epicerie.api.domain.ports.AdminPort;
import fedet.epicerie.api.persistence.mappers.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AdminRepository implements AdminPort {
    private final AdminRepositoryJPA adminRepositoryJPA;
    private final AdminMapper adminMapper;

    @Override
    public List<Admin> findAll() {
        return adminRepositoryJPA.findAll().stream()
                .map(adminMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Admin findById(UUID id) {
        return adminRepositoryJPA.findById(id)
                .map(adminMapper::toModel)
                .orElse(null);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepositoryJPA.findByEmail(email)
                .map(adminMapper::toModel)
                .orElse(null);
    }

    @Override
    public Admin save(Admin admin) {
        return adminMapper.toModel(
                adminRepositoryJPA.save(adminMapper.toEntity(admin))
        );
    }
}
