package com.worksafe.api.interfaces.mapper;

import com.worksafe.api.domain.entity.Recomendacao;
import com.worksafe.api.interfaces.dto.input.RecomendacaoRequest;
import com.worksafe.api.interfaces.dto.output.RecomendacaoResponse;

public final class RecomendacaoMapper {
    public static Recomendacao toModel(RecomendacaoRequest request) {
        return new Recomendacao(
                request.usuarioId(),
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
}
