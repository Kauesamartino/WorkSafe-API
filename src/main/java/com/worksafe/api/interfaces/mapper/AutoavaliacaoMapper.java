package com.worksafe.api.interfaces.mapper;

import com.worksafe.api.domain.entity.Autoavaliacao;
import com.worksafe.api.infrastructure.entity.JpaAutoavaliacaoEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.interfaces.dto.input.AutoavaliacaoRequest;
import com.worksafe.api.interfaces.dto.output.AutoavaliacaoResponse;

public final class AutoavaliacaoMapper {
    public static Autoavaliacao toModel(AutoavaliacaoRequest request, Long idUser) {
        return new Autoavaliacao(
                idUser,
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

    public static JpaAutoavaliacaoEntity toJpa(Autoavaliacao autoavaliacao, JpaUsuarioEntity jpaUsuario) {
        return new JpaAutoavaliacaoEntity(
                jpaUsuario,
                autoavaliacao.getData(),
                autoavaliacao.getEstresse(),
                autoavaliacao.getHumor(),
                autoavaliacao.getEnergia(),
                autoavaliacao.getQualidadeSono(),
                autoavaliacao.getComentarios()
        );
    }

    public static Autoavaliacao entityToDomain(JpaAutoavaliacaoEntity savedEntity) {
        return new Autoavaliacao(
                savedEntity.getId(),
                savedEntity.getJpaUsuarioEntity().getId(),
                savedEntity.getData(),
                savedEntity.getEstresse(),
                savedEntity.getHumor(),
                savedEntity.getEnergia(),
                savedEntity.getQualidadeSono(),
                savedEntity.getComentarios()
        );
    }
}
