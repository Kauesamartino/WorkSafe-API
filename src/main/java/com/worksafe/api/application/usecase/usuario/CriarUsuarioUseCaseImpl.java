package com.worksafe.api.application.usecase.usuario;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.application.exception.UsuarioUnsupportedOperation;
import com.worksafe.api.domain.entity.Role;
import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.domain.repository.RoleRepository;
import com.worksafe.api.domain.repository.UsuarioRepository;

public final class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final BuscarUsuarioPorCpfUseCase buscarUsuarioPorCpfUseCase;

    public CriarUsuarioUseCaseImpl(
            UsuarioRepository usuarioRepository,
            RoleRepository roleRepository,
            BuscarUsuarioPorCpfUseCase buscarUsuarioPorCpfUseCase
    ) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.buscarUsuarioPorCpfUseCase = buscarUsuarioPorCpfUseCase;
    }

    @Override
    public Usuario execute(Usuario usuario) {
        try{
            buscarUsuarioPorCpfUseCase.execute(usuario.getCpf());
        } catch (EntidadeNaoEncontradaException e){
            Role roleUser = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Role padrão não encontrada"));

            usuario.getCredenciais().adicionarRole(roleUser);
            return usuarioRepository.save(usuario);
        }
        throw new UsuarioUnsupportedOperation("Usuario já cadastrado");
    }
}
