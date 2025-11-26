package com.worksafe.api.infrastructure.persistance;

import com.worksafe.api.application.exception.EntidadeNaoEncontradaException;
import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.domain.logging.Logger;
import com.worksafe.api.domain.repository.AutoavaliacaoRepository;
import com.worksafe.api.infrastructure.entity.JpaAutoavaliacaoEntity;
import com.worksafe.api.infrastructure.entity.JpaCredenciaisEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.infrastructure.exception.InfraestruturaException;
import com.worksafe.api.infrastructure.repository.JpaAutoavaliacaoRepository;
import com.worksafe.api.infrastructure.repository.JpaCredenciaisRepository;
import com.worksafe.api.infrastructure.repository.JpaUsuarioRepository;
import com.worksafe.api.interfaces.mapper.AutoavaliacaoMapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class AutoavaliacaoRepositoryAdapter implements AutoavaliacaoRepository {

    private final JpaAutoavaliacaoRepository jpaAutoavaliacaoRepository;
    private final JpaCredenciaisRepository jpaCredenciaisRepository;
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final Logger logger;

    public AutoavaliacaoRepositoryAdapter(JpaAutoavaliacaoRepository jpaAutoavaliacaoRepository, JpaCredenciaisRepository jpaCredenciaisRepository, JpaUsuarioRepository jpaUsuarioRepository, Logger logger) {
        this.jpaAutoavaliacaoRepository = jpaAutoavaliacaoRepository;
        this.jpaCredenciaisRepository = jpaCredenciaisRepository;
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.logger = logger;
    }

    @Override
    public List<Autoavaliacao> findAll(Long idUser) {
        logger.info("Buscando todas as autoavaliações");
        JpaCredenciaisEntity jpaCredenciaisEntity = jpaCredenciaisRepository.getReferenceById(idUser);
        JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.findByCredenciais(jpaCredenciaisEntity)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com as credenciais ID: " + idUser));
        try {
            List<JpaAutoavaliacaoEntity> entities = jpaAutoavaliacaoRepository.findAllByJpaUsuarioEntity(jpaUsuarioEntity);
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
        try {
            JpaCredenciaisEntity jpaCredenciaisEntity = jpaCredenciaisRepository.getReferenceById(autoavaliacao.getUsuarioId());
            JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.findByCredenciais(jpaCredenciaisEntity)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com as credenciais ID: " + autoavaliacao.getUsuarioId()));
            JpaAutoavaliacaoEntity entity = AutoavaliacaoMapper.toJpa(autoavaliacao, jpaUsuarioEntity);
            JpaAutoavaliacaoEntity savedEntity = jpaAutoavaliacaoRepository.save(entity);
            logger.info("Autoavaliação salva com sucesso para o usuário com ID: " + autoavaliacao.getUsuarioId());
            return AutoavaliacaoMapper.entityToDomain(savedEntity);
        } catch (DataAccessException e) {
            logger.error("Erro ao salvar autoavaliação para o usuário com ID: " + autoavaliacao.getUsuarioId(), e);
            throw new InfraestruturaException("Erro ao salvar autoavaliação", e);
        }
    }
}
