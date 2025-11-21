package com.worksafe.api.interfaces.controller;

import com.worksafe.api.application.usecase.autoavaliacao.*;
import com.worksafe.api.application.usecase.usuario.BuscarUsuarioPorIdUseCase;
import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.interfaces.dto.input.AutoavaliacaoRequest;
import com.worksafe.api.interfaces.dto.output.AutoavaliacaoResponse;
import com.worksafe.api.interfaces.mapper.AutoavaliacaoMapper;

import java.util.List;

public final class AutoavaliacaoControllerImpl implements  AutoavaliacaoController {

    private final CreateAutoavaliacaoUseCase createAutoavaliacaoUseCase;
    private final BuscarAutoavaliacaoPorIdUseCase buscarAutoavaliacaoPorIdUseCase;
    private final ListarTodasAutoavaliacoesUseCase listarTodasAutoavaliacoesUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public AutoavaliacaoControllerImpl(CreateAutoavaliacaoUseCase createAutoavaliacaoUseCase, BuscarAutoavaliacaoPorIdUseCase buscarAutoavaliacaoPorIdUseCase, ListarTodasAutoavaliacoesUseCase listarTodasAutoavaliacoesUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        this.createAutoavaliacaoUseCase = createAutoavaliacaoUseCase;
        this.buscarAutoavaliacaoPorIdUseCase = buscarAutoavaliacaoPorIdUseCase;
        this.listarTodasAutoavaliacoesUseCase = listarTodasAutoavaliacoesUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
    }

    @Override
    public AutoavaliacaoResponse create(AutoavaliacaoRequest request) {
        Autoavaliacao autoavaliacao = AutoavaliacaoMapper.toModel(request);
        Autoavaliacao savedAutoavaliacao = createAutoavaliacaoUseCase.execute(autoavaliacao);
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(savedAutoavaliacao.getUsuarioId());
        return AutoavaliacaoMapper.toResponse(savedAutoavaliacao, usuario.getNome());
    }

    @Override
    public AutoavaliacaoResponse buscarPorId(Long id) {
        Autoavaliacao autoavaliacao = buscarAutoavaliacaoPorIdUseCase.execute(id);
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(autoavaliacao.getUsuarioId());
        return AutoavaliacaoMapper.toResponse(autoavaliacao, usuario.getNome());
    }

    @Override
    public List<AutoavaliacaoResponse> listarAvaliacoes() {
        return listarTodasAutoavaliacoesUseCase.execute()
                .stream()
                .map(autoavaliacao -> {
                    Usuario usuario = buscarUsuarioPorIdUseCase.execute(autoavaliacao.getUsuarioId());
                    return AutoavaliacaoMapper.toResponse(autoavaliacao, usuario.getNome());
                })
                .toList();
    }
}
