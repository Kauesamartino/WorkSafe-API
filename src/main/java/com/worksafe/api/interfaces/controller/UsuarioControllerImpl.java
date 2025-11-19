package com.worksafe.api.interfaces.controller;

import com.worksafe.api.application.usecase.usuario.*;
import com.worksafe.api.domain.entity.Usuario;
import com.worksafe.api.interfaces.dto.input.UsuarioRequest;
import com.worksafe.api.interfaces.dto.output.UsuarioDetailsResponse;
import com.worksafe.api.interfaces.dto.output.UsuarioResponse;
import com.worksafe.api.interfaces.mapper.UsuarioMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class UsuarioControllerImpl implements UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final BuscarUsuarioPorCpfUseCase buscarUsuarioPorCpfUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;
    private final DesativarUsuarioUseCase desativarUsuarioUseCase;

    public UsuarioControllerImpl(
            CriarUsuarioUseCase criarUsuarioUseCase,
            ListarUsuariosUseCase listarUsuariosUseCase,
            BuscarUsuarioPorCpfUseCase buscarUsuarioPorCpfUseCase,
            DeletarUsuarioUseCase deletarUsuarioUseCase,
            DesativarUsuarioUseCase desativarUsuarioUseCase
    ) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.buscarUsuarioPorCpfUseCase = buscarUsuarioPorCpfUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
        this.desativarUsuarioUseCase = desativarUsuarioUseCase;
    }

    @Override
    public UsuarioResponse create(UsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toModel(request);
        Usuario createdUsuario = criarUsuarioUseCase.execute(usuario);
        return UsuarioMapper.toResponse(createdUsuario);
    }

    @Override
    public Page<UsuarioDetailsResponse> listarTodosUsuarios(Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Usuario> usuariosPage = listarUsuariosUseCase.execute(pageable);
        return usuariosPage.map(UsuarioMapper::toDetailsResponse);
    }

    @Override
    public UsuarioResponse findByCpf(String cpf) {
        Usuario usuario = buscarUsuarioPorCpfUseCase.execute(cpf);
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    public void deleteByCpf(String cpf) {
        deletarUsuarioUseCase.execute(cpf);
    }

    @Override
    public void deactivateByCpf(String cpf) {
        desativarUsuarioUseCase.execute(cpf);
    }
}
