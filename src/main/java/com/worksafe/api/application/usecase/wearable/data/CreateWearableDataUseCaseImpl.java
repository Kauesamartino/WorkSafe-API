package com.worksafe.api.application.usecase.wearable.data;

import com.worksafe.api.domain.entity.WearableData;
import com.worksafe.api.domain.repository.WearableDataRepository;

public final class CreateWearableDataUseCaseImpl implements CreateWearableDataUseCase {

    private final WearableDataRepository wearableDataRepository;

    public CreateWearableDataUseCaseImpl(WearableDataRepository wearableDataRepository) {
        this.wearableDataRepository = wearableDataRepository;
    }

    @Override
    public WearableData execute(WearableData wearableData) {
        return wearableDataRepository.save(wearableData);
    }
}
