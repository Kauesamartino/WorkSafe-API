package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.domain.entity.Usuario;

public interface BuscarUsuarioPorUsernameUseCase {
    Usuario execute(String username);
}
