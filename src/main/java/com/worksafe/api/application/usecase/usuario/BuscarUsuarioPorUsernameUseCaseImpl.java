package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.domain.repository.UsuarioRepository;

public final class BuscarUsuarioPorUsernameUseCaseImpl implements BuscarUsuarioPorUsernameUseCase {

    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioPorUsernameUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario execute(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
