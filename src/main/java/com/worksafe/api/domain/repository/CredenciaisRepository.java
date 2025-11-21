package com.worksafe.api.domain.repository;

import com.worksafe.api.domain.entity.Credenciais;

import java.util.Optional;

public interface CredenciaisRepository {

    Optional<Credenciais> findByUsername(String username);


    Optional<Credenciais> findByUsernameWithRoles(String username);

    boolean existsByUsername(String username);

}
