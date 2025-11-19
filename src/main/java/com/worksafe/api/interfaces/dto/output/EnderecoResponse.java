package com.worksafe.api.interfaces.dto.output;

public record EnderecoResponse(
        String logradouro,

        String bairro,

        String cep,

        String numero,

        String complemento,

        String cidade,

        String uf) {
}
