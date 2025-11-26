package com.worksafe.api.domain.repository;

import com.worksafe.api.domain.entity.Recomendacao;

import java.util.List;

public interface RecomendacaoRepository {
    List<Recomendacao> findAll(Long idUser);

    Recomendacao findById(Long id);

    Recomendacao save(Recomendacao recomendacao);
}
