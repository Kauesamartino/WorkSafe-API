package com.worksafe.api.infrastructure.repository;

import com.worksafe.api.infrastructure.entity.JpaAutoavaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAutoavaliacaoRepository extends JpaRepository<JpaAutoavaliacaoEntity, Long> {
}
