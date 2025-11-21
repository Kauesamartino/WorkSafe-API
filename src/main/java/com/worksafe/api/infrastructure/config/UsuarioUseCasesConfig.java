package com.worksafe.api.infrastructure.config;

import com.worksafe.api.application.usecase.usuario.*;
import com.worksafe.api.domain.repository.RoleRepository;
import com.worksafe.api.domain.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioUseCasesConfig {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public UsuarioUseCasesConfig(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase(){
        return new CriarUsuarioUseCaseImpl(usuarioRepository, roleRepository, buscarUsuarioPorCpfUseCase());
    }

    @Bean
    public BuscarUsuarioPorCpfUseCase buscarUsuarioPorCpfUseCase(){
        return new BuscarUsuarioPorCpfUseCaseImpl(usuarioRepository);
    }

    @Bean
    public DeletarUsuarioUseCase deletarUsuarioUseCase(){
        return new DeletarUsuarioUseCaseImpl(usuarioRepository, buscarUsuarioPorCpfUseCase());
    }

    @Bean
    public DesativarUsuarioUseCase desativarUsuarioUseCase(){
        return new DesativarUsuarioUseCaseImpl(usuarioRepository, buscarUsuarioPorCpfUseCase());
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(){
        return new ListarUsuariosUseCaseImpl(usuarioRepository);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase(){
        return new BuscarUsuarioPorIdUseCaseImpl(usuarioRepository);
    }
}
