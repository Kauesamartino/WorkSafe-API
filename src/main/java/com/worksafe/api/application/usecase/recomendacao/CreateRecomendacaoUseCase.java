package com.worksafe.api.application.usecase.recomendacao;

import com.worksafe.api.domain.entity.Recomendacao;

public interface CreateRecomendacaoUseCase {
    Recomendacao execute(Recomendacao recomendacao);
}
