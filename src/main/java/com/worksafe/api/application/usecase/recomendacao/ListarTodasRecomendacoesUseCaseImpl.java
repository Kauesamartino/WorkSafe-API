package com.worksafe.api.application.usecase.recomendacao;

import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.domain.repository.RecomendacaoRepository;

import java.util.List;

public final class ListarTodasRecomendacoesUseCaseImpl implements ListarTodasRecomendacoesUseCase {

    private final RecomendacaoRepository recomendacaoRepository;

    public ListarTodasRecomendacoesUseCaseImpl(RecomendacaoRepository recomendacaoRepository) {
        this.recomendacaoRepository = recomendacaoRepository;
    }

    @Override
    public List<Recomendacao> execute() {
        return recomendacaoRepository.findAll();
    }
}
