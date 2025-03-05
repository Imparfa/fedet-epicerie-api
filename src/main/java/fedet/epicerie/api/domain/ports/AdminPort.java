package fedet.epicerie.api.domain.ports;

import fedet.epicerie.api.domain.models.Admin;

import java.util.List;
import java.util.UUID;

public interface AdminPort {
    List<Admin> findAll();

    Admin findById(UUID id);

    Admin findByEmail(String email);

    Admin save(Admin admin);
}
