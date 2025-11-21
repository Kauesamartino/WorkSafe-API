package com.worksafe.api.interfaces.mapper;

import com.worksafe.api.domain.entity.Alerta;
import com.worksafe.api.infrastructure.entity.JpaAlertaEntity;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.interfaces.dto.input.AlertaRequest;
import com.worksafe.api.interfaces.dto.output.AlertaDetailsResponse;
import com.worksafe.api.interfaces.dto.output.AlertaResponse;

public final class AlertaMapper {
    public static Alerta toModel(AlertaRequest request) {
        return new Alerta(
                request.usuarioId(),
                request.tipoAlerta(),
                request.descricao(),
                request.severidade()
        );
    }

    public static AlertaResponse toResponse(Alerta createdAlerta, String nomeUsuario) {
        return new AlertaResponse(
                createdAlerta.getId(),
                nomeUsuario,
                createdAlerta.getTipoAlerta(),
                createdAlerta.getDescricao(),
                createdAlerta.getSeveridade(),
                createdAlerta.getData()
        );
    }

    public static AlertaDetailsResponse toDetailsResponse(Alerta alerta, String nome) {
        return new AlertaDetailsResponse(
                nome,
                alerta.getTipoAlerta(),
                alerta.getDescricao(),
                alerta.getSeveridade(),
                alerta.getData()
        );
    }

    public static Alerta entityToDomain(JpaAlertaEntity entity) {
        return new Alerta(
                entity.getId(),
                entity.getJpaUsuarioEntity().getId(),
                entity.getTipoAlerta(),
                entity.getDescricao(),
                entity.getSeveridade(),
                entity.getData(),
                entity.getResolvido()
        );
    }

    public static JpaAlertaEntity toJpa(Alerta alerta, JpaUsuarioEntity jpaUsuarioEntity) {
        return new JpaAlertaEntity(
                jpaUsuarioEntity,
                alerta.getTipoAlerta(),
                alerta.getDescricao(),
                alerta.getSeveridade(),
                alerta.getData(),
                alerta.getResolvido()
        );
    }
}
