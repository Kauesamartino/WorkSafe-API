package com.worksafe.api.interfaces.controller;

import com.worksafe.api.interfaces.dto.input.AlertaRequest;
import com.worksafe.api.interfaces.dto.output.AlertaDetailsResponse;
import com.worksafe.api.interfaces.dto.output.AlertaResponse;

import java.util.List;

public interface AlertaController {
    /**
     * Cria um novo alerta com base na requisição fornecida.
     *
     * @param request A requisição contendo os dados do alerta a ser criado.
     * @return A resposta contendo os detalhes do alerta criado.
     */
    AlertaResponse create(AlertaRequest request);

    /**
     * Busca um alerta pelo seu ID.
     *
     * @param id O ID do alerta a ser buscado.
     * @return A resposta contendo os detalhes do alerta encontrado.
     */
    AlertaResponse findById(Long id);

    /**
     * Lista todos os alertas existentes.
     *
     * @return Uma lista contendo os detalhes de todos os alertas.
     */
    List<AlertaDetailsResponse> listarTodosAlertas();
}
