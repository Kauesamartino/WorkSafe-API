package com.worksafe.api.interfaces.mapper;

import com.worksafe.api.domain.entity.Role;
import com.worksafe.api.infrastructure.entity.JpaRoleEntity;

import java.util.Optional;

public final class RoleMapper {

    public static Optional<Role> toDomain(JpaRoleEntity entity) {
        return Optional.of(new Role(
                entity.getName(),
                entity.getDescription()
        ));
    }
}