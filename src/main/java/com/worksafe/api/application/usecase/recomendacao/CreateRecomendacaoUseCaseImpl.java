package com.worksafe.api.application.usecase.recomendacao;

import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.domain.repository.RecomendacaoRepository;

public final class CreateRecomendacaoUseCaseImpl implements CreateRecomendacaoUseCase {

    private final RecomendacaoRepository recomendacaoRepository;

    public CreateRecomendacaoUseCaseImpl(RecomendacaoRepository recomendacaoRepository) {
        this.recomendacaoRepository = recomendacaoRepository;
    }

    @Override
    public Recomendacao execute(Recomendacao recomendacao) {
        System.out.println(recomendacao.getUsuarioId());
        return recomendacaoRepository.save(recomendacao);
    }
}
