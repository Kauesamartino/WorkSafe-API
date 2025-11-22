package com.worksafe.api.application.usecase.wearable.data;

import com.worksafe.api.domain.entity.WearableData;

public interface CreateWearableDataUseCase {
    WearableData execute(WearableData wearableData);
}
