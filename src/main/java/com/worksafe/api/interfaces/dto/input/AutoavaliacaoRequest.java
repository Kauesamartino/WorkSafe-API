package com.worksafe.api.interfaces.dto.input;

public record AutoavaliacaoRequest(
        Long usuarioId,
        Integer estresse,
        Integer humor,
        Integer energia,
        Integer qualidadeSono,
        String comentarios
) {
}
