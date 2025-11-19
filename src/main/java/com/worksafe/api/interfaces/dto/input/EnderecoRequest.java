package com.worksafe.api.interfaces.dto.input;

public record EnderecoRequest(
        String logradouro,
        String bairro,
        String cep,
        String numero,
        String complemento,
        String cidade,
        String uf
) {
}
