package com.worksafe.api.interfaces.controller;

import com.worksafe.api.application.usecase.alerta.BuscarAlertaPorIdUseCase;
import com.worksafe.api.application.usecase.alerta.CriarAlertaUseCase;
import com.worksafe.api.application.usecase.alerta.ListarTodosAlertasUseCase;
import com.worksafe.api.application.usecase.usuario.BuscarUsuarioPorIdUseCase;
import com.worksafe.api.domain.entity.Alerta;
import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.interfaces.dto.input.AlertaRequest;
import com.worksafe.api.interfaces.dto.output.AlertaDetailsResponse;
import com.worksafe.api.interfaces.dto.output.AlertaResponse;
import com.worksafe.api.interfaces.mapper.AlertaMapper;

import java.util.List;

public class AlertaControllerImpl implements AlertaController {

    private final CriarAlertaUseCase criarAlertaUseCase;
    private final BuscarAlertaPorIdUseCase buscarAlertaPorIdUseCase;
    private final ListarTodosAlertasUseCase listarTodosAlertasUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public AlertaControllerImpl(CriarAlertaUseCase criarAlertaUseCase, BuscarAlertaPorIdUseCase buscarAlertaPorIdUseCase, ListarTodosAlertasUseCase listarTodosAlertasUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        this.criarAlertaUseCase = criarAlertaUseCase;
        this.buscarAlertaPorIdUseCase = buscarAlertaPorIdUseCase;
        this.listarTodosAlertasUseCase = listarTodosAlertasUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
    }

    @Override
    public AlertaResponse create(AlertaRequest request) {
        Alerta alerta = AlertaMapper.toModel(request);
        Alerta createdAlerta = criarAlertaUseCase.execute(alerta);
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(createdAlerta.getUsuarioId());
        return AlertaMapper.toResponse(createdAlerta, usuario.getNome());
    }

    @Override
    public AlertaResponse findById(Long id) {
        Alerta alerta = buscarAlertaPorIdUseCase.execute(id);
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(alerta.getUsuarioId());
        return AlertaMapper.toResponse(alerta, usuario.getNome());
    }

    @Override
    public List<AlertaDetailsResponse> listarTodosAlertas() {
        List<Alerta> alertas = listarTodosAlertasUseCase.execute();
        return alertas.stream()
                .map(alerta -> {
                    Usuario usuario = buscarUsuarioPorIdUseCase.execute(alerta.getUsuarioId());
                    return AlertaMapper.toDetailsResponse(alerta, usuario.getNome());
                })
                .toList();
    }
}
