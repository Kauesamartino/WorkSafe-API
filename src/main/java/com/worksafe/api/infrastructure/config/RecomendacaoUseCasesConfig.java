package com.worksafe.api.infrastructure.config;

import com.worksafe.api.application.usecase.recomendacao.*;
import com.worksafe.api.application.usecase.usuario.BuscarUsuarioPorIdUseCase;
import com.worksafe.api.domain.repository.RecomendacaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecomendacaoUseCasesConfig {

    private final RecomendacaoRepository recomendacaoRepository;


    public RecomendacaoUseCasesConfig(RecomendacaoRepository recomendacaoRepository) {
        this.recomendacaoRepository = recomendacaoRepository;
    }

    @Bean
    public CreateRecomendacaoUseCase createRecomendacaoUseCase(){
        return new CreateRecomendacaoUseCaseImpl(recomendacaoRepository);
    }

    @Bean
    public BuscarRecomendacaoPorIdUseCase buscarUsuarioPorIdUseCase(){
        return new BuscarRecomendacaoPorIdUseCaseImpl(recomendacaoRepository);
    }

    @Bean
    public ListarTodasRecomendacoesUseCase listarTodasRecomendacoesUseCase(){
        return new ListarTodasRecomendacoesUseCaseImpl(recomendacaoRepository);
    }
}
