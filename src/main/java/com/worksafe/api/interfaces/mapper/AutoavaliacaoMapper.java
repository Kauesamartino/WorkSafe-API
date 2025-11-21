package com.worksafe.api.interfaces.mapper;

import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.interfaces.dto.input.AutoavaliacaoRequest;
import com.worksafe.api.interfaces.dto.output.AutoavaliacaoResponse;

public class AutoavaliacaoMapper {
    public static Autoavaliacao toModel(AutoavaliacaoRequest request) {
        return new Autoavaliacao(
                request.usuarioId(),
                request.estresse(),
                request.humor(),
                request.energia(),
                request.qualidadeSono(),
                request.comentarios()
        );
    }

    public static AutoavaliacaoResponse toResponse(Autoavaliacao savedAutoavaliacao, String usuarioName) {
        return new AutoavaliacaoResponse(
                savedAutoavaliacao.getId(),
                usuarioName,
                savedAutoavaliacao.getData(),
                savedAutoavaliacao.getEstresse(),
                savedAutoavaliacao.getHumor(),
                savedAutoavaliacao.getEnergia(),
                savedAutoavaliacao.getQualidadeSono(),
                savedAutoavaliacao.getComentarios()
        );
    }
}
