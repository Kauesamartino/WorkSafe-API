package com.worksafe.api.infrastructure.config;

import com.worksafe.api.application.usecase.recomendacao.ListarTodasRecomendacoesUseCase;
import com.worksafe.api.application.usecase.alerta.BuscarAlertaPorIdUseCase;
import com.worksafe.api.application.usecase.alerta.CriarAlertaUseCase;
import com.worksafe.api.application.usecase.alerta.ListarTodosAlertasUseCase;
import com.worksafe.api.application.usecase.recomendacao.BuscarRecomendacaoPorIdUseCase;
import com.worksafe.api.application.usecase.recomendacao.CreateRecomendacaoUseCase;
import com.worksafe.api.application.usecase.usuario.*;
import com.worksafe.api.infrastructure.security.JwtUtil;
import com.worksafe.api.interfaces.controller.*;
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

    @Bean
    public AlertaController alertaController(
            CriarAlertaUseCase criarAlertaUseCase, ListarTodosAlertasUseCase listarTodosAlertasUseCase,
            BuscarAlertaPorIdUseCase buscarAlertaPorIdUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase
    ) {
        return new AlertaControllerImpl(
                criarAlertaUseCase, buscarAlertaPorIdUseCase,
                listarTodosAlertasUseCase, buscarUsuarioPorIdUseCase);
    }

    @Bean
    public RecomendacaoController recomendacaoController(
            CreateRecomendacaoUseCase createRecomendacaoUseCase, BuscarRecomendacaoPorIdUseCase buscarRecomendacaoPorIdUseCase,
            ListarTodasRecomendacoesUseCase listarTodasRecomendacoesUseCase
    ){
        return new RecomendacaoControllerImpl(
                createRecomendacaoUseCase, buscarRecomendacaoPorIdUseCase,
                listarTodasRecomendacoesUseCase);
    }
}
