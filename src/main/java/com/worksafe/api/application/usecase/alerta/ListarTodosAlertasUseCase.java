package com.worksafe.api.application.usecase.alerta;

import com.worksafe.api.domain.entity.Alerta;

import java.util.List;

public interface ListarTodosAlertasUseCase {
    List<Alerta> execute();
}
