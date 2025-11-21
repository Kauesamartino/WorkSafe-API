package com.worksafe.api.interfaces.dto.input;

import java.time.LocalDate;

public record UsuarioRequest(
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String telefone,
        CredenciaisRequest credenciais,
        String cargo,
        String departamento,
        LocalDate dataNascimento,
        EnderecoRequest endereco
) {
}
