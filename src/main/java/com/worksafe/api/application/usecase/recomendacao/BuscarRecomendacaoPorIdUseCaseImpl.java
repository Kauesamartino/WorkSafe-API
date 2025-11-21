package com.worksafe.api.application.usecase.recomendacao;

import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.domain.repository.RecomendacaoRepository;

public final class BuscarRecomendacaoPorIdUseCaseImpl implements  BuscarRecomendacaoPorIdUseCase {

    private final RecomendacaoRepository recomendacaoRepository;

    public BuscarRecomendacaoPorIdUseCaseImpl(RecomendacaoRepository recomendacaoRepository) {
        this.recomendacaoRepository = recomendacaoRepository;
    }

    @Override
    public Recomendacao execute(Long id) {
        return recomendacaoRepository.findById(id);
    }
}
