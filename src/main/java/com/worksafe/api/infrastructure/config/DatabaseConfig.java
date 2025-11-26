package com.worksafe.api.infrastructure.config;

import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.*;
import com.worksafe.api.infrastructure.logging.LoggerFactory;
import com.worksafe.api.infrastructure.persistance.*;
import com.worksafe.api.infrastructure.repository.*;
import com.worksafe.api.infrastructure.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseConfig {

    @Bean
    public UsuarioRepository clienteRepository(JpaUsuarioRepository jpaUsuarioRepository, JpaCredenciaisRepository jpaCredenciaisRepository, JpaRoleRepository jpaRoleRepository) {
        final Logger logger = LoggerFactory.getLogger(UsuarioRepositoryAdapter.class);
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new UsuarioRepositoryAdapter(jpaUsuarioRepository, jpaCredenciaisRepository, jpaRoleRepository, passwordEncoder, logger);
    }

    @Bean
    public RoleRepository roleRepository(JpaRoleRepository jpaRoleRepository) {
        final Logger logger = LoggerFactory.getLogger(UsuarioRepositoryAdapter.class);
        return new RoleRepositoryAdapter(jpaRoleRepository, logger);
    }

    @Bean
    public AlertaRepository alertaRepository(JpaAlertaRepository jpaAlertaRepository, JpaUsuarioRepository jpaUsuarioRepository) {
        final Logger logger = LoggerFactory.getLogger(UsuarioRepositoryAdapter.class);
        return new AlertaRepositoryAdapter(jpaAlertaRepository, jpaUsuarioRepository, logger);
    }

    @Bean
    public RecomendacaoRepository recomendacaoRepository(JpaRecomendacaoRepository jpaRecomendacaoRepository, JpaUsuarioRepository jpaUsuarioRepository, JpaCredenciaisRepository jpaCredenciaisRepository) {
        final Logger logger = LoggerFactory.getLogger(RecomendacaoRepositoryAdapter.class);
        return new RecomendacaoRepositoryAdapter(jpaRecomendacaoRepository, jpaUsuarioRepository, jpaCredenciaisRepository, logger);
    }

    @Bean
    public AutoavaliacaoRepository autoavaliacaoRepository(JpaAutoavaliacaoRepository jpaAutoavaliacaoRepository, JpaUsuarioRepository jpaUsuarioRepository, JpaCredenciaisRepository jpaCredenciaisRepository) {
        final Logger logger = LoggerFactory.getLogger(AutoavaliacaoRepositoryAdapter.class);
        return new AutoavaliacaoRepositoryAdapter(jpaAutoavaliacaoRepository, jpaCredenciaisRepository, jpaUsuarioRepository, logger);
    }

    @Bean
    public WearableDataRepository wearableDataRepository(JpaWearableDataRepository jpaWearableDataRepository, JpaUsuarioRepository jpaUsuarioRepository) {
        final Logger logger = LoggerFactory.getLogger(WearableDataRepositoryAdapter.class);
        return new WearableDataRepositoryAdapter(jpaWearableDataRepository, jpaUsuarioRepository, logger);
    }

    @Bean
    public CredenciaisRepository credenciaisRepository(JpaCredenciaisRepository jpaCredenciaisRepository) {
        final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
        return new CredenciaisRepositoryAdapter(jpaCredenciaisRepository, logger);
    }
}
