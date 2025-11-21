package com.worksafe.api.application.usecase.alerta;

import com.worksafe.api.domain.entity.Alerta;
import com.worksafe.api.domain.repository.AlertaRepository;

public final class CriarAlertaUseCaseImpl implements  CriarAlertaUseCase {

    private final AlertaRepository alertaRepository;

    public CriarAlertaUseCaseImpl(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Override
    public Alerta execute(Alerta alerta) {
        return alertaRepository.create(alerta);
    }
}
