package com.worksafe.api.interfaces.mapper;

import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.infrastructure.entity.JpaRecomendacaoEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.interfaces.dto.input.RecomendacaoRequest;
import com.worksafe.api.interfaces.dto.output.RecomendacaoResponse;

public final class RecomendacaoMapper {
    public static Recomendacao toModel(RecomendacaoRequest request, Long idUser) {
        return new Recomendacao(
                idUser,
                request.tipoAtividade(),
                request.titulo(),
                request.descricao()
        );
    }

    public static RecomendacaoResponse toResponse(Recomendacao createdRecomendacao) {
        return new RecomendacaoResponse(
                createdRecomendacao.getId(),
                createdRecomendacao.getUsuarioId(),
                createdRecomendacao.getTipoAtividade(),
                createdRecomendacao.getTitulo(),
                createdRecomendacao.getDescricao(),
                createdRecomendacao.getCreatedAt()
        );
    }

    public static JpaRecomendacaoEntity toJpa(Recomendacao recomendacao, JpaUsuarioEntity jpaUsuarioEntity) {
        return new JpaRecomendacaoEntity(
                jpaUsuarioEntity,
                recomendacao.getTipoAtividade(),
                recomendacao.getTitulo(),
                recomendacao.getDescricao(),
                recomendacao.getCreatedAt(),
                recomendacao.getConsumido()
        );
    }

    public static Recomendacao toDomain(JpaRecomendacaoEntity savedEntity) {
        return new Recomendacao(
                savedEntity.getId(),
                savedEntity.getJpaUsuarioEntity().getId(),
                savedEntity.getTipoAtividade(),
                savedEntity.getTitulo(),
                savedEntity.getDescricao(),
                savedEntity.getCreatedAt(),
                savedEntity.getConsumido()
        );
    }
}
