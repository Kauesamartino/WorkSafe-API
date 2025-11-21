package com.worksafe.api.infrastructure.repository;


import com.worksafe.api.infrastructure.entity.JpaCredenciaisEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntity, Long> {

    Optional<JpaUsuarioEntity> findByCpf(String cpf);

    List<JpaUsuarioEntity> findAllByAtivoTrue();

    Optional<JpaUsuarioEntity> findByCredenciais(JpaCredenciaisEntity credenciais);
}
