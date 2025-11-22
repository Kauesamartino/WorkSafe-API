package com.worksafe.api.application.usecase.wearable.data;

import com.worksafe.api.domain.entity.WearableData;
import com.worksafe.api.domain.repository.WearableDataRepository;

public final class ListarWearableDataUseCaseImpl implements BuscarWearableDataByIdUseCase {

    private final WearableDataRepository wearableDataRepository;

    public ListarWearableDataUseCaseImpl(WearableDataRepository wearableDataRepository) {
        this.wearableDataRepository = wearableDataRepository;
    }

    @Override
    public WearableData execute(Long id) {
        return wearableDataRepository.findAll(id);
    }
}
