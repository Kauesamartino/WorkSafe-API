package com.worksafe.api.infrastructure.persistance;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.AutoavaliacaoRepository;
import com.worksafe.api.infrastructure.entity.JpaAutoavaliacaoEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.infrastructure.exception.InfraestruturaException;
import com.worksafe.api.infrastructure.repository.JpaAutoavaliacaoRepository;
import com.worksafe.api.infrastructure.repository.JpaUsuarioRepository;
import com.worksafe.api.interfaces.mapper.AutoavaliacaoMapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class AutoavaliacaoRepositoryAdapter implements AutoavaliacaoRepository {

    private final JpaAutoavaliacaoRepository jpaAutoavaliacaoRepository;
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final Logger logger;

    public AutoavaliacaoRepositoryAdapter(JpaAutoavaliacaoRepository jpaAutoavaliacaoRepository, JpaUsuarioRepository jpaUsuarioRepository, Logger logger) {
        this.jpaAutoavaliacaoRepository = jpaAutoavaliacaoRepository;
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.logger = logger;
    }

    @Override
    public List<Autoavaliacao> findAll() {
        logger.info("Buscando todas as autoavaliações");

        try {
            List<JpaAutoavaliacaoEntity> entities = jpaAutoavaliacaoRepository.findAll();
            logger.info("Autoavaliações encontradas: " + entities.size());
            return entities.stream()
                    .map(AutoavaliacaoMapper::entityToDomain)
                    .toList();
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar autoavaliações", e);
            throw new InfraestruturaException("Erro ao buscar autoavaliações", e);
        }
    }

    @Override
    public Autoavaliacao findById(Long id) {
        logger.info("Buscando autoavaliação com o ID: " + id);

        try {
            JpaAutoavaliacaoEntity entity = jpaAutoavaliacaoRepository.findById(id)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Autoavaliação não encontrada: " + id));
            logger.info("Autoavaliação encontrada com o ID: " + id);
            return AutoavaliacaoMapper.entityToDomain(entity);
        } catch (DataAccessException e) {
            logger.error("Erro ao buscar autoavaliação com o ID: " + id, e);
            throw new InfraestruturaException("Erro ao buscar autoavaliação", e);
        }
    }

    @Override
    public Autoavaliacao save(Autoavaliacao autoavaliacao) {
        logger.info("Salvando autoavaliação para o usuário com ID: " + autoavaliacao.getUsuarioId());
        JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.getReferenceById(autoavaliacao.getUsuarioId());
        JpaAutoavaliacaoEntity entity = AutoavaliacaoMapper.toJpa(autoavaliacao, jpaUsuarioEntity);

        try {
            JpaAutoavaliacaoEntity savedEntity = jpaAutoavaliacaoRepository.save(entity);
            logger.info("Autoavaliação salva com sucesso para o usuário com ID: " + autoavaliacao.getUsuarioId());
            return AutoavaliacaoMapper.entityToDomain(savedEntity);
        } catch (DataAccessException e) {
            logger.error("Erro ao salvar autoavaliação para o usuário com ID: " + autoavaliacao.getUsuarioId(), e);
            throw new InfraestruturaException("Erro ao salvar autoavaliação", e);
        }
    }
}
