package com.worksafe.api.interfaces.dto.output;

import java.time.LocalDate;

public record RecomendacaoResponse(
        Long id,
        Long usuarioId,
        String tipoAtividade,
        String titulo,
        String descricao,
        LocalDate createdAt
) {
}
