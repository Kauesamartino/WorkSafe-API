package com.worksafe.api.infrastructure.persistance;

import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.RecomendacaoRepository;
import com.worksafe.api.infrastructure.entity.JpaRecomendacaoEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.infrastructure.exception.InfraestruturaException;
import com.worksafe.api.infrastructure.repository.JpaRecomendacaoRepository;
import com.worksafe.api.infrastructure.repository.JpaUsuarioRepository;
import com.worksafe.api.interfaces.mapper.RecomendacaoMapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class RecomendacaoRepositoryAdapter implements RecomendacaoRepository {

    private final JpaRecomendacaoRepository jpaRecomendacaoRepository;
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final Logger logger;

    public RecomendacaoRepositoryAdapter(JpaRecomendacaoRepository jpaRecomendacaoRepository, JpaUsuarioRepository jpaUsuarioRepository, Logger logger) {
        this.jpaRecomendacaoRepository = jpaRecomendacaoRepository;
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.logger = logger;
    }

    @Override
    public List<Recomendacao> findAll() {
        logger.info("Buscando todas as recomendacoes");
        try {
            List<JpaRecomendacaoEntity> entities = jpaRecomendacaoRepository.findAll();
            logger.info("Recomendacoes encontradas: " + entities.size());
            return entities.stream()
                    .map(RecomendacaoMapper::toDomain)
                    .toList();
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar todas as recomendacoes: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar todas as recomendacoes: " + e.getMessage());
        }
    }

    @Override
    public Recomendacao findById(Long id) {
        logger.info("Buscando Recomendacao por ID: " + id);
        try {
            JpaRecomendacaoEntity entity = jpaRecomendacaoRepository.findById(id)
                    .orElseThrow(() -> new InfraestruturaException("Recomendacao não encontrada com ID: " + id));
            logger.info("Recomendacao encontrada: " + entity);
            return RecomendacaoMapper.toDomain(entity);
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar recomendacao por ID: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar recomendacao por ID: " + e.getMessage());
        }
    }

    @Override
    public Recomendacao save(Recomendacao recomendacao) {
        logger.info("Salvando recomendacao: " + recomendacao);
        JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.getReferenceById(recomendacao.getUsuarioId());
        JpaRecomendacaoEntity entity = RecomendacaoMapper.toJpa(recomendacao, jpaUsuarioEntity);
        try {
            JpaRecomendacaoEntity savedEntity = jpaRecomendacaoRepository.save(entity);
            logger.info("Recomendacao salva com sucesso: " + savedEntity);
            return RecomendacaoMapper.toDomain(savedEntity);
        } catch (DataAccessException e) {
            logger.error("Erro ao salvar recomendacao: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao salvar recomendacao: " + e.getMessage());
        }
    }
}
