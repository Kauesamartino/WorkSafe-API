package com.worksafe.api.application.usecase.autoavaliacao;

import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.domain.repository.AutoavaliacaoRepository;

public final class BuscarAutoavaliacaoPorIdUseCaseImpl implements BuscarAutoavaliacaoPorIdUseCase {

    private final AutoavaliacaoRepository autoavaliacaoRepository;

    public BuscarAutoavaliacaoPorIdUseCaseImpl(AutoavaliacaoRepository autoavaliacaoRepository) {
        this.autoavaliacaoRepository = autoavaliacaoRepository;
    }

    @Override
    public Autoavaliacao execute(Long id) {
        return autoavaliacaoRepository.findById(id);
    }
}
