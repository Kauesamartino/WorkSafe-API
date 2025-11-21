package com.worksafe.api.application.usecase.alerta;

import com.worksafe.api.domain.entity.Alerta;
import com.worksafe.api.domain.repository.AlertaRepository;

import java.util.List;

public final class ListarTodosAlertasUseCaseImpl implements  ListarTodosAlertasUseCase {

    private final AlertaRepository alertaRepository;

    public ListarTodosAlertasUseCaseImpl(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Override
    public List<Alerta> execute() {
        return alertaRepository.findAllByAtivoTrue();
    }
}
