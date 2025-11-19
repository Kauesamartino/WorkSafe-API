package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.domain.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public final class ListarUsuariosUseCaseImpl implements ListarUsuariosUseCase {

    private final UsuarioRepository usuarioRepository;

    public ListarUsuariosUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<Usuario> execute(Pageable pageable) {
        return usuarioRepository.findAllByAtivoTrue(pageable);
    }
}
