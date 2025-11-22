package com.worksafe.api.application.usecase.wearable.data;

import com.worksafe.api.domain.entity.WearableData;
import com.worksafe.api.domain.repository.WearableDataRepository;

import java.util.List;

public final class ListarWearableDataUseCaseImpl implements ListarWearableDataUseCase {

    private final WearableDataRepository wearableDataRepository;

    public ListarWearableDataUseCaseImpl(WearableDataRepository wearableDataRepository) {
        this.wearableDataRepository = wearableDataRepository;
    }

    @Override
    public List<WearableData> execute() {
        return wearableDataRepository.findAll();
    }
}
