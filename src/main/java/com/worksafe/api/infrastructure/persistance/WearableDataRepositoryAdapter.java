package com.worksafe.api.infrastructure.persistance;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.WearableData;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.WearableDataRepository;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.infrastructure.entity.JpaWearableDataEntity;
import com.worksafe.api.infrastructure.exception.InfraestruturaException;
import com.worksafe.api.infrastructure.repository.JpaUsuarioRepository;
import com.worksafe.api.infrastructure.repository.JpaWearableDataRepository;
import com.worksafe.api.interfaces.mapper.WearableMapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class WearableDataRepositoryAdapter implements WearableDataRepository {

    private final JpaWearableDataRepository jpaWearableDataRepository;
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final Logger logger;

    public WearableDataRepositoryAdapter(JpaWearableDataRepository jpaWearableDataRepository, JpaUsuarioRepository jpaUsuarioRepository, Logger logger) {
        this.jpaWearableDataRepository = jpaWearableDataRepository;
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.logger = logger;
    }

    @Override
    public WearableData findById(Long id) {
        logger.info("Buscando WearableData com o id " + id);

        try {
            JpaWearableDataEntity entity = jpaWearableDataRepository.findById(id)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("WearableData não encontrado: " + id));
            logger.info("WearableData encontrado com o id " + id);
            return WearableMapper.entityToDomain(entity);
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar WearableData: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar WearableData", e);
        }
    }

    @Override
    public WearableData save(WearableData wearableData) {
        logger.info("Iniciando persistencia de WearableData");
        try {
            JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.getReferenceById(wearableData.getUsuarioId());
            JpaWearableDataEntity entity = WearableMapper.domainToEntity(wearableData, jpaUsuarioEntity);
            JpaWearableDataEntity savedEntity = jpaWearableDataRepository.save(entity);
            logger.info("WearableData persistido com sucesso para o usuário com id " + wearableData.getUsuarioId());
            return WearableMapper.entityToDomain(savedEntity);
        } catch (DataAccessException e) {
            logger.error("Erro ao persistir WearableData: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao persistir WearableData", e);
        }
    }

    @Override
    public List<WearableData> findAll() {
        logger.info("Buscando todos os WearableData");
        try{
            List<JpaWearableDataEntity> entities = jpaWearableDataRepository.findAll();
            logger.info("WearableData encontrados: " + entities.size());
            return entities.stream()
                    .map(WearableMapper::entityToDomain)
                    .toList();
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar WearableData: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar WearableData", e);
        }
    }
}
