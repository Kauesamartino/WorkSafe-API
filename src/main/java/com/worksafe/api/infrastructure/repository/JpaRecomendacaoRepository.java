package com.worksafe.api.infrastructure.repository;

import com.worksafe.api.infrastructure.entity.JpaRecomendacaoEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRecomendacaoRepository extends JpaRepository<JpaRecomendacaoEntity, Long> {
    List<JpaRecomendacaoEntity> findAllByJpaUsuarioEntity(JpaUsuarioEntity jpaUsuarioEntity);
}
