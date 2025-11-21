package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.domain.entity.Usuario;

public interface BuscarUsuarioPorIdUseCase {
    Usuario execute(Long id);
}
