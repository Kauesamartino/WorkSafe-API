package com.worksafe.api.infrastructure.repository;

import com.worksafe.api.infrastructure.entity.JpaAutoavaliacaoEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAutoavaliacaoRepository extends JpaRepository<JpaAutoavaliacaoEntity, Long> {
    List<JpaAutoavaliacaoEntity> findAllByJpaUsuarioEntity(JpaUsuarioEntity jpaUsuarioEntity);
}
