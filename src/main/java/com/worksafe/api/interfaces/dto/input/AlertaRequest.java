package com.worksafe.api.interfaces.dto.input;


import com.worksafe.api.domain.enums.Severidade;
import com.worksafe.api.domain.enums.TipoAlerta;

public record AlertaRequest(
        Long usuarioId,
        String descricao,
        TipoAlerta tipoAlerta,
        Severidade severidade
) {
}
