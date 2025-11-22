package com.worksafe.api.application.usecase.wearable.data;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.WearableData;
import com.worksafe.api.domain.repository.WearableDataRepository;

public final class CreateWearableDataUseCaseImpl implements CreateWearableDataUseCase {

    private final WearableDataRepository wearableDataRepository;

    public CreateWearableDataUseCaseImpl(WearableDataRepository wearableDataRepository) {
        this.wearableDataRepository = wearableDataRepository;
    }

    @Override
    public WearableData execute(WearableData wearableData) {
        try {
            wearableDataRepository.findById(wearableData.getId());
        } catch (EntidadeNaoEncontradaException ex) {
            return wearableDataRepository.save(wearableData);
        }
        throw new UnsupportedOperationException("Wearable data with id " + wearableData.getId() + " already exists.");
    }
}
