package com.worksafe.api.infrastructure.repository;

import com.worksafe.api.infrastructure.entity.JpaAlertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAlertaRepository extends JpaRepository<JpaAlertaEntity, Long> {
}
