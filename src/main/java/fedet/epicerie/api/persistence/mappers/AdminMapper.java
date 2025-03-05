package fedet.epicerie.api.persistence.mappers;

import fedet.epicerie.api.domain.models.Admin;
import fedet.epicerie.api.persistence.entities.AdminEty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toModel(AdminEty entity);

    AdminEty toEntity(Admin model);
}
