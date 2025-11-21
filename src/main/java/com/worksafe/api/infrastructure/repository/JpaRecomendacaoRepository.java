package com.worksafe.api.infrastructure.repository;

import com.worksafe.api.infrastructure.entity.JpaRecomendacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRecomendacaoRepository extends JpaRepository<JpaRecomendacaoEntity, Long> {
}
