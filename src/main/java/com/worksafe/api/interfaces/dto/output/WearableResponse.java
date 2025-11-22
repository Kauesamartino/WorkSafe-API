package com.worksafe.api.interfaces.dto.output;

import java.time.LocalDateTime;

public record WearableResponse(
        Long id,
        String nomeUsuario,
        LocalDateTime data,
        Double batimentosMedia,
        Integer passos,
        Double sonoTotal
) {
}
