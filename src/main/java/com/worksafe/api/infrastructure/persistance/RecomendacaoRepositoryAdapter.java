package com.worksafe.api.infrastructure.persistance;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.RecomendacaoRepository;
import com.worksafe.api.infrastructure.entity.JpaCredenciaisEntity;
import com.worksafe.api.infrastructure.entity.JpaRecomendacaoEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.infrastructure.exception.InfraestruturaException;
import com.worksafe.api.infrastructure.repository.JpaCredenciaisRepository;
import com.worksafe.api.infrastructure.repository.JpaRecomendacaoRepository;
import com.worksafe.api.infrastructure.repository.JpaUsuarioRepository;
import com.worksafe.api.interfaces.mapper.RecomendacaoMapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class RecomendacaoRepositoryAdapter implements RecomendacaoRepository {

    private final JpaRecomendacaoRepository jpaRecomendacaoRepository;
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final JpaCredenciaisRepository jpaCredenciaisRepository;
    private final Logger logger;

    public RecomendacaoRepositoryAdapter(JpaRecomendacaoRepository jpaRecomendacaoRepository, JpaUsuarioRepository jpaUsuarioRepository, JpaCredenciaisRepository jpaCredenciaisRepository, Logger logger) {
        this.jpaRecomendacaoRepository = jpaRecomendacaoRepository;
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.jpaCredenciaisRepository = jpaCredenciaisRepository;
        this.logger = logger;
    }

    @Override
    public List<Recomendacao> findAll(Long idUser) {
        logger.info("Buscando todas as recomendacoes");
        JpaCredenciaisEntity jpaCredenciaisEntity = jpaCredenciaisRepository.getReferenceById(idUser);
        JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.findByCredenciais(jpaCredenciaisEntity)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com as credenciais ID: " + idUser));
        try {
            List<JpaRecomendacaoEntity> entities = jpaRecomendacaoRepository.findAllByJpaUsuarioEntity(jpaUsuarioEntity);
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
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Recomendacao não encontrada com ID: " + id));
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
        JpaCredenciaisEntity jpaCredenciaisEntity = jpaCredenciaisRepository.getReferenceById(recomendacao.getUsuarioId());
        JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.findByCredenciais(jpaCredenciaisEntity)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com as credenciais ID: " + recomendacao.getUsuarioId()));
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
