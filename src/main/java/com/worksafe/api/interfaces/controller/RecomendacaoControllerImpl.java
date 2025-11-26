package com.worksafe.api.interfaces.controller;

import com.worksafe.api.application.usecase.recomendacao.BuscarRecomendacaoPorIdUseCase;
import com.worksafe.api.application.usecase.recomendacao.CreateRecomendacaoUseCase;
import com.worksafe.api.application.usecase.recomendacao.ListarTodasRecomendacoesUseCase;
import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.interfaces.dto.input.RecomendacaoRequest;
import com.worksafe.api.interfaces.dto.output.RecomendacaoResponse;
import com.worksafe.api.interfaces.mapper.RecomendacaoMapper;

import java.util.List;

public final class RecomendacaoControllerImpl implements  RecomendacaoController {

    private final CreateRecomendacaoUseCase createRecomendacaoUseCase;
    private final BuscarRecomendacaoPorIdUseCase buscarRecomendacaoPorIdUseCase;
    private final ListarTodasRecomendacoesUseCase listarTodasRecomendacoesUseCase;

    public RecomendacaoControllerImpl(CreateRecomendacaoUseCase createRecomendacaoUseCase, BuscarRecomendacaoPorIdUseCase buscarRecomendacaoPorIdUseCase, ListarTodasRecomendacoesUseCase listarTodasRecomendacoesUseCase) {
        this.createRecomendacaoUseCase = createRecomendacaoUseCase;
        this.buscarRecomendacaoPorIdUseCase = buscarRecomendacaoPorIdUseCase;
        this.listarTodasRecomendacoesUseCase = listarTodasRecomendacoesUseCase;
    }

    @Override
    public RecomendacaoResponse create(RecomendacaoRequest request, Long idUser) {
        Recomendacao recomendacao = RecomendacaoMapper.toModel(request, idUser);
        Recomendacao createdRecomendacao = createRecomendacaoUseCase.execute(recomendacao);
        return RecomendacaoMapper.toResponse(createdRecomendacao);
    }

    @Override
    public RecomendacaoResponse buscarPorId(Long id) {
        Recomendacao recomendacao = buscarRecomendacaoPorIdUseCase.execute(id);
        return RecomendacaoMapper.toResponse(recomendacao);
    }

    @Override
    public List<RecomendacaoResponse> listarTodasRecomendacoes(Long idUser) {
        List<Recomendacao> recomendacoes = listarTodasRecomendacoesUseCase.execute(idUser);
        return recomendacoes.stream()
                .map(RecomendacaoMapper::toResponse)
                .toList();
    }
}
