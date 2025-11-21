package com.worksafe.api.interfaces.controller;

import com.worksafe.api.application.usecase.alerta.BuscarAlertaPorIdUseCase;
import com.worksafe.api.application.usecase.alerta.CriarAlertaUseCase;
import com.worksafe.api.application.usecase.alerta.ListarTodosAlertasUseCase;
import com.worksafe.api.domain.entity.Alerta;
import com.worksafe.api.interfaces.dto.input.AlertaRequest;
import com.worksafe.api.interfaces.dto.output.AlertaDetailsResponse;
import com.worksafe.api.interfaces.dto.output.AlertaResponse;

import java.util.List;

public class AlertaControllerImpl implements AlertaController {

    private final CriarAlertaUseCase criarAlertaUseCase;
    private final BuscarAlertaPorIdUseCase buscarAlertaPorIdUseCase;
    private final ListarTodosAlertasUseCase listarTodosAlertasUseCase;

    public AlertaControllerImpl(CriarAlertaUseCase criarAlertaUseCase, BuscarAlertaPorIdUseCase buscarAlertaPorIdUseCase, ListarTodosAlertasUseCase listarTodosAlertasUseCase) {
        this.criarAlertaUseCase = criarAlertaUseCase;
        this.buscarAlertaPorIdUseCase = buscarAlertaPorIdUseCase;
        this.listarTodosAlertasUseCase = listarTodosAlertasUseCase;
    }

    @Override
    public AlertaResponse create(AlertaRequest request) {
        Alerta alerta = AlertaMapper.toModel(request);
        Alerta createdAlerta = criarAlertaUseCase.execute(alerta);
        return AlertaMapper.toResponse(createdAlerta);
    }

    @Override
    public AlertaResponse findById(Long id) {
        Alerta alerta = buscarAlertaPorIdUseCase.execute(id);
        return AlertaMapper.toResponse(alerta);
    }

    @Override
    public List<AlertaDetailsResponse> listarTodosAlertas() {
        List<Alerta> alertas = listarTodosAlertasUseCase.execute();
        return alertas.stream()
                .map(AlertaMapper::toDetailsResponse)
                .toList();
    }
}
