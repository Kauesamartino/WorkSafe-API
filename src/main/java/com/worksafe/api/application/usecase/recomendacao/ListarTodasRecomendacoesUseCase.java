package com.worksafe.api.application.usecase.recomendacao;

import com.worksafe.api.domain.entity.Recomendacao;

import java.util.List;

public interface ListarTodasRecomendacoesUseCase {
    List<Recomendacao> execute(Long idUser);
}
