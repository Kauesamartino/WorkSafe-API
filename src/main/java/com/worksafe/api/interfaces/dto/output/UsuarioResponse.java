package com.worksafe.api.interfaces.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponse(
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String telefone,
        String cargo,
        String departamento,
        LocalDate dataNascimento,
        LocalDateTime createdAt,
        EnderecoResponse endereco
) {
}
