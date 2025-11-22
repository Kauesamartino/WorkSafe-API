package com.worksafe.api.infrastructure.persistance;


import com.worksafe.api.domain.entity.Credenciais;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.CredenciaisRepository;
import com.worksafe.api.infrastructure.entity.JpaCredenciaisEntity;
import com.worksafe.api.infrastructure.exception.InfraestruturaException;
import com.worksafe.api.infrastructure.repository.JpaCredenciaisRepository;
import com.worksafe.api.interfaces.mapper.CredenciaisMapper;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

public class CredenciaisRepositoryAdapter implements CredenciaisRepository {

    private final JpaCredenciaisRepository jpaCredenciaisRepository;
    private final Logger logger;

    public CredenciaisRepositoryAdapter(JpaCredenciaisRepository jpaCredenciaisRepository, Logger logger) {
        this.jpaCredenciaisRepository = jpaCredenciaisRepository;
        this.logger = logger;
    }

    @Override
    public Optional<Credenciais> findByUsername(String username) {
        logger.info("Buscando credenciais por username: " + username);

        try {
            logger.info("Credenciais encontradas para username: " + username);
            JpaCredenciaisEntity jpaCredenciaisEntity = jpaCredenciaisRepository.findByUsername(username);
            return CredenciaisMapper.jpaEntityToDomain(jpaCredenciaisEntity);
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar credenciais: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar credenciais: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Credenciais> findByUsernameWithRoles(String username) {
        logger.info("Buscando credenciais por username: " + username);

        try {
            JpaCredenciaisEntity jpaCredenciaisEntity = jpaCredenciaisRepository.findByUsernameWithRoles(username);
            logger.info("Credenciais encontradas para username: " + username);
            return CredenciaisMapper.jpaEntityToDomain(jpaCredenciaisEntity);
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar credenciais: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar credenciais: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }
}
