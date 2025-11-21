package com.worksafe.api.infrastructure.persistance;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.Alerta;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.AlertaRepository;
import com.worksafe.api.infrastructure.entity.JpaAlertaEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.infrastructure.repository.JpaAlertaRepository;
import com.worksafe.api.infrastructure.repository.JpaUsuarioRepository;
import com.worksafe.api.interfaces.mapper.AlertaMapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class AlertaRepositoryAdapter implements AlertaRepository {

    private final JpaAlertaRepository jpaAlertaRepository;
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final Logger logger;

    public AlertaRepositoryAdapter(JpaAlertaRepository jpaAlertaRepository, JpaUsuarioRepository jpaUsuarioRepository, Logger logger) {
        this.jpaAlertaRepository = jpaAlertaRepository;
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.logger = logger;
    }

    @Override
    public Alerta findById(Long id) {
        logger.info("Buscando alerta com o id " + id);

        try{
            JpaAlertaEntity entity = jpaAlertaRepository.findById(id)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Alerta não encontrado: " + id));
            logger.info("Alerta encontrado com o id " + id);
            return AlertaMapper.entityToDomain(entity);
        } catch (DataAccessException e){
            logger.error("Erro ao buscar alerta: " + e.getMessage(), e);
            throw new EntidadeNaoEncontradaException("Erro ao buscar alerta: " + e.getMessage());
        }
    }

    @Override
    public Alerta create(Alerta alerta) {
        logger.info("Criando um novo alerta para o usuário com id " + alerta.getUsuarioId());
        JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.getReferenceById(alerta.getUsuarioId());
        JpaAlertaEntity entity = AlertaMapper.toJpa(alerta, jpaUsuarioEntity);

        try{
            JpaAlertaEntity savedEntity = jpaAlertaRepository.save(entity);
            logger.info("Alerta criado com sucesso para o usuário com id " + alerta.getUsuarioId());
            return AlertaMapper.entityToDomain(savedEntity);
        } catch (DataAccessException e){
            logger.error("Erro ao criar alerta: " + e.getMessage(), e);
            throw new EntidadeNaoEncontradaException("Erro ao criar alerta: " + e.getMessage());
        }
    }

    @Override
    public List<Alerta> findAll() {
        logger.info("Buscando todos os alertas");

        try{
            List<JpaAlertaEntity> entities = jpaAlertaRepository.findAll();
            logger.info("Total de alertas encontrados: " + entities.size());
            return entities.stream()
                    .map(AlertaMapper::entityToDomain)
                    .toList();
        } catch (DataAccessException e){
            logger.error("Erro ao buscar alertas: " + e.getMessage(), e);
            throw new EntidadeNaoEncontradaException("Erro ao buscar alertas: " + e.getMessage());
        }
    }
}
