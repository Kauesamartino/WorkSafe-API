package com.worksafe.api.application.usecase.autoavaliacao;


import com.worksafe.api.domain.entity.Autoavaliacao;

import java.util.List;

public interface ListarTodasAutoavaliacoesUseCase {
    List<Autoavaliacao> execute(Long idUser);
}
