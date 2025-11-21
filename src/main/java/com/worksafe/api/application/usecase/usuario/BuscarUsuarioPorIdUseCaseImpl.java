package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.domain.repository.UsuarioRepository;

public final class BuscarUsuarioPorIdUseCaseImpl implements  BuscarUsuarioPorIdUseCase {

    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioPorIdUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario execute(Long id) {
        return usuarioRepository.findById(id);
    }
}
