package com.worksafe.api.application.usecase.autoavaliacao;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.domain.repository.AutoavaliacaoRepository;

public final class CreateAutoavaliacaoUseCaseImpl implements CreateAutoavaliacaoUseCase{

    private final AutoavaliacaoRepository autoavaliacaoRepository;

    public CreateAutoavaliacaoUseCaseImpl(AutoavaliacaoRepository autoavaliacaoRepository) {
        this.autoavaliacaoRepository = autoavaliacaoRepository;
    }

    @Override
    public Autoavaliacao execute(Autoavaliacao autoavaliacao) {
        try {
            autoavaliacaoRepository.findById(autoavaliacao.getId());
        } catch (EntidadeNaoEncontradaException e) {
            return autoavaliacaoRepository.save(autoavaliacao);
        }
        throw new UnsupportedOperationException("Autoavaliação com o ID " + autoavaliacao);
    }
}
