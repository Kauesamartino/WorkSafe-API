package com.worksafe.api.interfaces.dto.input;

public record WearableRequest(
        Long usuarioId,
        Double batimentosMedia,
        Integer passos,
        Double sonoTotal
) {
}
