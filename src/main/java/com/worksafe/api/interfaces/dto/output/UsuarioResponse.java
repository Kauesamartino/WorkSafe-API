package com.worksafe.api.interfaces.dto.output;

import java.time.LocalDateTime;

public record UsuarioResponse(
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String telefone,
        String cargo,
        String departamento,
        LocalDateTime createdAt,
        EnderecoResponse endereco
) {
}
