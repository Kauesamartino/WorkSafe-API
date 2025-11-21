package com.worksafe.api.application.usecase.autoavaliacao;

import com.worksafe.api.domain.entity.Autoavaliacao;

public interface BuscarAutoavaliacaoPorIdUseCase {
    Autoavaliacao execute(Long id);
}
