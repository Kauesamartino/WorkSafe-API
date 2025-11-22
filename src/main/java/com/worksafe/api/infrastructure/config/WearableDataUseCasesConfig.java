package com.worksafe.api.infrastructure.config;

import com.worksafe.api.application.usecase.wearable.data.*;
import com.worksafe.api.domain.repository.WearableDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WearableDataUseCasesConfig {

    private final WearableDataRepository wearableDataRepository;

    public WearableDataUseCasesConfig(WearableDataRepository wearableDataRepository) {
        this.wearableDataRepository = wearableDataRepository;
    }

    @Bean
    public CreateWearableDataUseCase createWearableDataUseCase() {
        return new CreateWearableDataUseCaseImpl(wearableDataRepository);
    }

    @Bean
    public ListarWearableDataUseCase listarWearableDataUseCase() {
        return new ListarWearableDataUseCaseImpl(wearableDataRepository);
    }

    @Bean
    public BuscarWearableDataByIdUseCase buscarWearableDataByIdUseCase() {
        return new BuscarWearableDataByIdUseCaseImpl(wearableDataRepository);
    }
}
