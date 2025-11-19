package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.repository.UsuarioRepository;

public final class DesativarUsuarioUseCaseImpl implements DesativarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final BuscarUsuarioPorCpfUseCase buscarUsuarioPorCpfUseCase;

    public DesativarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, BuscarUsuarioPorCpfUseCase buscarUsuarioPorCpfUseCase) {
        this.usuarioRepository = usuarioRepository;
        this.buscarUsuarioPorCpfUseCase = buscarUsuarioPorCpfUseCase;
    }

    @Override
    public void execute(String cpf) {
        if (buscarUsuarioPorCpfUseCase.execute(cpf) == null){
            throw new EntidadeNaoEncontradaException("Usuário não encontrado para o CPF informado.");
        }
        usuarioRepository.deactivate(cpf);
    }
}
