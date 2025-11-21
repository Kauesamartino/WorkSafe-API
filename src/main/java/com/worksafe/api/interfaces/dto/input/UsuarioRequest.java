package com.worksafe.api.interfaces.dto.input;

import com.worksafe.api.domain.enums.Sexo;

import java.time.LocalDate;

public record UsuarioRequest(
        String nome,
        String sobrenome,
        String cpf,
        Sexo sexo,
        String email,
        String telefone,
        CredenciaisRequest credenciais,
        String cargo,
        String departamento,
        LocalDate dataNascimento,
        EnderecoRequest endereco
) {
}
