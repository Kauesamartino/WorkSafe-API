package com.worksafe.api.infrastructure.config;

import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.AlertaRepository;
import com.worksafe.api.domain.repository.RoleRepository;
import com.worksafe.api.domain.repository.UsuarioRepository;
import com.worksafe.api.infrastructure.logging.LoggerFactory;
import com.worksafe.api.infrastructure.persistance.*;
import com.worksafe.api.infrastructure.repository.JpaAlertaRepository;
import com.worksafe.api.infrastructure.repository.JpaCredenciaisRepository;
import com.worksafe.api.infrastructure.repository.JpaRoleRepository;
import com.worksafe.api.infrastructure.repository.JpaUsuarioRepository;
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
    public AlertaRepository alertaRepository(JpaAlertaRepository jpaAlertaRepository) {
        final Logger logger = LoggerFactory.getLogger(UsuarioRepositoryAdapter.class);
        return new AlertaRepositoryAdapter(jpaAlertaRepository, logger);
    }
}
