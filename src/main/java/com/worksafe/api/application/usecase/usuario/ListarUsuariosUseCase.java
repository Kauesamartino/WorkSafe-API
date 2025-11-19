package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.domain.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarUsuariosUseCase {
    Page<Usuario> execute(Pageable pageable);
}
