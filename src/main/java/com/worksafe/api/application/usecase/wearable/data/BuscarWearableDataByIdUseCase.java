package com.worksafe.api.application.usecase.wearable.data;

import com.worksafe.api.domain.entity.WearableData;

public interface BuscarWearableDataByIdUseCase {
    WearableData execute(Long id);
}
