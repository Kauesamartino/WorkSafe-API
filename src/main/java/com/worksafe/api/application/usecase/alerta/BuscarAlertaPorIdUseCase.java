package com.worksafe.api.application.usecase.alerta;

import com.worksafe.api.domain.entity.Alerta;

public interface BuscarAlertaPorIdUseCase {
    Alerta execute(Long id);
}
