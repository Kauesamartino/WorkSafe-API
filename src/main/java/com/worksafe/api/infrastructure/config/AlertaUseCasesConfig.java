package com.worksafe.api.infrastructure.config;

import com.worksafe.api.application.usecase.alerta.*;
import com.worksafe.api.domain.repository.AlertaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlertaUseCasesConfig {

    private final AlertaRepository alertaRepository;

    public AlertaUseCasesConfig(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Bean
    public ListarTodosAlertasUseCase listarTodosAlertasUseCase(){
        return new ListarTodosAlertasUseCaseImpl(alertaRepository);
    }

    @Bean
    public BuscarAlertaPorIdUseCase buscarAlertaPorIdUseCase(){
        return new BuscarAlertaPorIdUseCaseImpl(alertaRepository);
    }

    @Bean
    public CriarAlertaUseCase criarAlertaUseCase(){
        return new CriarAlertaUseCaseImpl(alertaRepository);
    }
}
