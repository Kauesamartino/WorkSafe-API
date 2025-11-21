package com.worksafe.api.interfaces.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioDetailsResponse(
        String nome,
        String sobrenome,
        String cpf,
        String cargo,
        String departamento,
        LocalDate dataDeNascimento,
        LocalDateTime createdAt
) {
}
