package com.worksafe.api.application.usecase.alerta;

import com.worksafe.api.domain.entity.Alerta;
import com.worksafe.api.domain.repository.AlertaRepository;

public final class BuscarAlertaPorIdUseCaseImpl implements  BuscarAlertaPorIdUseCase {

    private final AlertaRepository alertaRepository;

    public BuscarAlertaPorIdUseCaseImpl(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }


    @Override
    public Alerta execute(Long id) {
        return alertaRepository.findById(id);
    }
}
