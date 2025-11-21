package com.worksafe.api.interfaces.dto.input;


public record AlertaRequest(
        Long usuarioId,
        String descricao,
        String tipoAlerta,
        String severidade
) {
}
