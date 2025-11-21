package com.worksafe.api.interfaces.controller;

import com.worksafe.api.application.usecase.autoavaliacao.*;
import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.interfaces.dto.input.AutoavaliacaoRequest;
import com.worksafe.api.interfaces.dto.output.AutoavaliacaoResponse;
import com.worksafe.api.interfaces.mapper.AutoavaliacaoMapper;

import java.util.List;

public final class AutoavaliacaoControllerImpl implements  AutoavaliacaoController {

    private final CreateAutoavaliacaoUseCase createAutoavaliacaoUseCase;
    private final BuscarAutoavaliacaoPorIdUseCase buscarAutoavaliacaoPorIdUseCase;
    private final ListarTodasAutoavaliacoesUseCase listarTodasAutoavaliacoesUseCase;

    public AutoavaliacaoControllerImpl(CreateAutoavaliacaoUseCase createAutoavaliacaoUseCase, BuscarAutoavaliacaoPorIdUseCase buscarAutoavaliacaoPorIdUseCase, ListarTodasAutoavaliacoesUseCase listarTodasAutoavaliacoesUseCase) {
        this.createAutoavaliacaoUseCase = createAutoavaliacaoUseCase;
        this.buscarAutoavaliacaoPorIdUseCase = buscarAutoavaliacaoPorIdUseCase;
        this.listarTodasAutoavaliacoesUseCase = listarTodasAutoavaliacoesUseCase;
    }

    @Override
    public AutoavaliacaoResponse create(AutoavaliacaoRequest request) {
        Autoavaliacao autoavaliacao = AutoavaliacaoMapper.toModel(request);
        Autoavaliacao savedAutoavaliacao = createAutoavaliacaoUseCase.execute(autoavaliacao);
        return AutoavaliacaoMapper.toResponse(savedAutoavaliacao);
    }

    @Override
    public AutoavaliacaoResponse buscarPorId(Long id) {
        Autoavaliacao autoavaliacao = buscarAutoavaliacaoPorIdUseCase.execute(id);
        return AutoavaliacaoMapper.toResponse(autoavaliacao);
    }

    @Override
    public List<AutoavaliacaoResponse> listarAvaliacoes() {
        return listarTodasAutoavaliacoesUseCase.execute()
                .stream()
                .map(AutoavaliacaoMapper::toResponse)
                .toList();
    }
}
