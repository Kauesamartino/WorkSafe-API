package com.worksafe.api.application.usecase.autoavaliacao;

import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.domain.repository.AutoavaliacaoRepository;

import java.util.List;

public final class ListarTodasAutoavaliacoesUseCaseImpl implements  ListarTodasAutoavaliacoesUseCase {

    private final AutoavaliacaoRepository autoavaliacaoRepository;

    public ListarTodasAutoavaliacoesUseCaseImpl(AutoavaliacaoRepository autoavaliacaoRepository) {
        this.autoavaliacaoRepository = autoavaliacaoRepository;
    }

    @Override
    public List<Autoavaliacao> execute(Long idUser) {
        return autoavaliacaoRepository.findAll(idUser);
    }
}
