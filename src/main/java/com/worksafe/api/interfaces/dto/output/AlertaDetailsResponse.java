package com.worksafe.api.interfaces.dto.output;

import com.worksafe.api.domain.enums.Severidade;
import com.worksafe.api.domain.enums.TipoAlerta;

import java.time.LocalDateTime;

public record AlertaDetailsResponse(
        String nomeUsuario,
        TipoAlerta tipoAlerta,
        String descricao,
        Severidade severidade,
        LocalDateTime data
) {
}
