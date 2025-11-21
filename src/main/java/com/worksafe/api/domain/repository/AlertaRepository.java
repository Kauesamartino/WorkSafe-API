package com.worksafe.api.domain.repository;

import com.worksafe.api.domain.entity.Alerta;

import java.util.List;

public interface AlertaRepository {
    Alerta findById(Long id);

    Alerta create(Alerta alerta);

    List<Alerta> findAll();
}
