package com.worksafe.api.domain.repository;

import com.worksafe.api.domain.entity.WearableData;

public interface WearableDataRepository {
    WearableData findById(Long id);

    WearableData save(WearableData wearableData);

    WearableData findAll(Long id);
}
