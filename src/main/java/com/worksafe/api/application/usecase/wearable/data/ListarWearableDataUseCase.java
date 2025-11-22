package com.worksafe.api.application.usecase.wearable.data;

import com.worksafe.api.domain.entity.WearableData;

import java.util.List;

public interface ListarWearableDataUseCase {
    List<WearableData> execute();
}
