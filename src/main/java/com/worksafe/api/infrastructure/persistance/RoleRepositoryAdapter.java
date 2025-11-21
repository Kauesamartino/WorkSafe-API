package com.worksafe.api.infrastructure.persistance;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.Role;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.RoleRepository;
import com.worksafe.api.infrastructure.entity.JpaRoleEntity;
import com.worksafe.api.infrastructure.exception.InfraestruturaException;
import com.worksafe.api.infrastructure.repository.JpaRoleRepository;
import com.worksafe.api.interfaces.mapper.RoleMapper;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

public class RoleRepositoryAdapter implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;
    private final Logger logger;

    public RoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository, Logger logger) {
        this.jpaRoleRepository = jpaRoleRepository;
        this.logger = logger;
    }

    @Override
    public Optional<Role> findByName(String name) {
        logger.info("Buscando role com o nome " + name);

        try{
            JpaRoleEntity entity = jpaRoleRepository.findByName(name)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Role não encontrada: " + name));
            return RoleMapper.toDomain(entity);
        } catch (DataAccessException e){
            logger.error("Erro ao buscar role: " + e.getMessage(), e);
            throw new InfraestruturaException(e.getMessage());
        }
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
