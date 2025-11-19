package com.worksafe.api.interfaces.dto.input;

public record UsuarioRequest(
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String telefone,
        CredenciaisRequest credenciais,
        String cargo,
        String departamento,
        EnderecoRequest endereco
) {
}
