package com.worksafe.api.infrastructure.repository;

import com.worksafe.api.infrastructure.entity.JpaWearableDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWearableDataRepository extends JpaRepository<JpaWearableDataEntity, Long> {
}
