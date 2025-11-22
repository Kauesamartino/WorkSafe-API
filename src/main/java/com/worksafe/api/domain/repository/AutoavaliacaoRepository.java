package com.worksafe.api.domain.repository;

import com.worksafe.api.domain.entity.Autoavaliacao;

import java.util.List;

public interface AutoavaliacaoRepository {
    List<Autoavaliacao> findAll();

    Autoavaliacao findById(Long id);

    Autoavaliacao save(Autoavaliacao autoavaliacao);
}
