package com.worksafe.api.domain.repository;

import com.worksafe.api.domain.entity.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
