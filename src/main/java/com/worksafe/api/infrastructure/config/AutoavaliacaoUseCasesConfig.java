package com.worksafe.api.infrastructure.config;

import com.worksafe.api.application.usecase.autoavaliacao.*;
import com.worksafe.api.domain.repository.AutoavaliacaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoavaliacaoUseCasesConfig {

    private final AutoavaliacaoRepository autoavaliacaoRepository;

    public AutoavaliacaoUseCasesConfig(AutoavaliacaoRepository autoavaliacaoRepository) {
        this.autoavaliacaoRepository = autoavaliacaoRepository;
    }

    @Bean
    public CreateAutoavaliacaoUseCase createAutoavaliacaoUseCase() {
        return new CreateAutoavaliacaoUseCaseImpl(autoavaliacaoRepository);
    }

    @Bean
    public BuscarAutoavaliacaoPorIdUseCase buscarAutoavaliacaoPorIdUseCase() {
        return new BuscarAutoavaliacaoPorIdUseCaseImpl(autoavaliacaoRepository);
    }

    @Bean
    public ListarTodasAutoavaliacoesUseCase listarTodasAutoavaliacoesUseCase() {
        return new ListarTodasAutoavaliacoesUseCaseImpl(autoavaliacaoRepository);
    }
}
