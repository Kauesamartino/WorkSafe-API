package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.domain.repository.UsuarioRepository;

public final class BuscarUsuarioPorCpfUseCaseImpl implements BuscarUsuarioPorCpfUseCase {

    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioPorCpfUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario execute(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}
