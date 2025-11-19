package com.worksafe.api.infrastructure.config;

import com.worksafe.api.application.usecase.usuario.*;
import com.worksafe.api.infrastructure.security.JwtUtil;
import com.worksafe.api.interfaces.controller.AuthController;
import com.worksafe.api.interfaces.controller.AuthControllerImpl;
import com.worksafe.api.interfaces.controller.UsuarioController;
import com.worksafe.api.interfaces.controller.UsuarioControllerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class ControllersConfig {

    @Bean
    public AuthController authController(
            AuthenticationManager authenticationManager, JwtUtil jwtUtil
    ) {
        return new AuthControllerImpl(authenticationManager, jwtUtil);
    }

    @Bean
    public UsuarioController usuarioController(
            CriarUsuarioUseCase criarUsuarioUseCase, ListarUsuariosUseCase listarUsuariosUseCase,
            BuscarUsuarioPorCpfUseCase buscarUsuarioPorCpfUseCase, DeletarUsuarioUseCase deletarUsuarioUseCase,
            DesativarUsuarioUseCase desativarUsuarioUseCase
    ) {
        return new UsuarioControllerImpl(
                criarUsuarioUseCase, listarUsuariosUseCase,
                buscarUsuarioPorCpfUseCase, deletarUsuarioUseCase,
                desativarUsuarioUseCase);
    }
}
