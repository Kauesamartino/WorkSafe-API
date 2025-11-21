package com.worksafe.api.application.usecase.recomendacao;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.application.exception.UsuarioUnsupportedOperation;
import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.domain.repository.RecomendacaoRepository;

public final class CreateRecomendacaoUseCaseImpl implements CreateRecomendacaoUseCase {

    private final RecomendacaoRepository recomendacaoRepository;

    public CreateRecomendacaoUseCaseImpl(RecomendacaoRepository recomendacaoRepository) {
        this.recomendacaoRepository = recomendacaoRepository;
    }


    @Override
    public Recomendacao execute(Recomendacao recomendacao) {
        try {
            recomendacaoRepository.findById(recomendacao.getId());
        } catch (EntidadeNaoEncontradaException e) {
            return recomendacaoRepository.save(recomendacao);
        }
        throw new UsuarioUnsupportedOperation("Recomendacao ja cadastrada");
    }
}
