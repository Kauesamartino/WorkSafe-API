package com.worksafe.api.domain.repository;

import com.worksafe.api.domain.entity.WearableData;

import java.util.List;

public interface WearableDataRepository {
    WearableData findById(Long id);

    WearableData save(WearableData wearableData);

    List<WearableData> findAll();
}
