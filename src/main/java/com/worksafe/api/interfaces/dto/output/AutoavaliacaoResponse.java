package com.worksafe.api.interfaces.dto.output;

import java.time.LocalDate;

public record AutoavaliacaoResponse(
        Long id,
        String usuarioName,
        LocalDate data,
        Integer estresse,
        Integer humor,
        Integer energia,
        Integer qualidadeSono,
        String comentarios
) {
}
