package com.worksafe.api.interfaces.mapper;

import com.worksafe.api.domain.entity.WearableData;
import com.worksafe.api.infrastructure.entity.JpaUsuarioEntity;
import com.worksafe.api.infrastructure.entity.JpaWearableDataEntity;
import com.worksafe.api.interfaces.dto.input.WearableRequest;
import com.worksafe.api.interfaces.dto.output.WearableResponse;

public class WearableMapper {
    public static WearableData toModel(WearableRequest request) {
        return new WearableData(
                request.usuarioId(),
                request.batimentosMedia(),
                request.passos(),
                request.sonoTotal()
        );
    }

    public static WearableResponse toResponse(WearableData createdWearableData, String nome) {
        return new WearableResponse(
                createdWearableData.getId(),
                nome,
                createdWearableData.getData(),
                createdWearableData.getBatimentosMedia(),
                createdWearableData.getPassos(),
                createdWearableData.getSonoTotal()
        );
    }

    public static WearableData entityToDomain(JpaWearableDataEntity jpaWearableDataEntity) {
        return new WearableData(
                jpaWearableDataEntity.getId(),
                jpaWearableDataEntity.getJpaUsuarioEntity().getId(),
                jpaWearableDataEntity.getData(),
                jpaWearableDataEntity.getBatimentosMedia(),
                jpaWearableDataEntity.getPassos(),
                jpaWearableDataEntity.getSonoTotal()
        );
    }

    public static JpaWearableDataEntity domainToEntity(WearableData wearableData, JpaUsuarioEntity jpaUsuarioEntity) {
        return new JpaWearableDataEntity(
                jpaUsuarioEntity,
                wearableData.getData(),
                wearableData.getBatimentosMedia(),
                wearableData.getPassos(),
                wearableData.getSonoTotal()
        );
    }
}
