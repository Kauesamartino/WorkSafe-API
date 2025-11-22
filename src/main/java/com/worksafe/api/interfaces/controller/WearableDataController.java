package com.worksafe.api.interfaces.controller;

import com.worksafe.api.interfaces.dto.input.WearableRequest;
import com.worksafe.api.interfaces.dto.output.WearableResponse;

import java.util.List;

public interface WearableDataController {

    /**
     * Cria um novo registro de dados de dispositivo vestível.
     *
     * @param request Objeto contendo os dados do dispositivo vestível a serem criados.
     * @return Objeto de resposta contendo os detalhes do registro criado.
     */
    WearableResponse create(WearableRequest request);

    /**
     * Busca um registro de dados de dispositivo vestível pelo seu ID.
     *
     * @param id ID do registro a ser buscado.
     * @return Objeto de resposta contendo os detalhes do registro encontrado.
     */
    WearableResponse buscarPorId(Long id);

    /**
     * Lista todos os registros de dados de dispositivos vestíveis.
     *
     * @return Lista de objetos de resposta contendo os detalhes dos registros encontrados.
     */
    List<WearableResponse> listarDados();
}
