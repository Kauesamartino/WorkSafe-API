package com.worksafe.api.interfaces.dto.output;

import com.worksafe.api.domain.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponse(
        String nome,
        String sobrenome,
        String cpf,
        Sexo sexo,
        String email,
        String telefone,
        String cargo,
        String departamento,
        LocalDate dataNascimento,
        LocalDateTime createdAt,
        EnderecoResponse endereco
) {
}
