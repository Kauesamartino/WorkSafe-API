package com.worksafe.api.infrastructure.repository;


import com.worksafe.api.infrastructure.entity.JpaCredenciaisEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntity, Long> {

    Optional<JpaUsuarioEntity> findByCpf(String cpf);

    Page<JpaUsuarioEntity> findAllByAtivoTrue(Pageable pageable);

    Optional<JpaUsuarioEntity> findByCredenciais(JpaCredenciaisEntity credenciais);
}
