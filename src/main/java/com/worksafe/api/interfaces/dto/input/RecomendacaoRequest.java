package com.worksafe.api.interfaces.dto.input;

public record RecomendacaoRequest(
        Long usuarioId,
        String tipoAtividade,
        String titulo,
        String descricao
) {
}
