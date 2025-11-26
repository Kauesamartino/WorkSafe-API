package com.worksafe.api.interfaces.controller;

import com.worksafe.api.interfaces.dto.input.AutoavaliacaoRequest;
import com.worksafe.api.interfaces.dto.output.AutoavaliacaoResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface AutoavaliacaoController {
    /**
     * Cria uma nova autoavaliação com base na requisição fornecida.
     *
     * @param request A requisição contendo os dados da autoavaliação.
     * @return A resposta contendo os detalhes da autoavaliação criada.
     */
    AutoavaliacaoResponse create(@Valid AutoavaliacaoRequest request, Long idUser);

    /**
     * Busca uma autoavaliação pelo seu ID.
     *
     * @param id O ID da autoavaliação a ser buscada.
     * @return A resposta contendo os detalhes da autoavaliação encontrada.
     */
    AutoavaliacaoResponse buscarPorId(Long id);

    /**
     * Lista todas as autoavaliações disponíveis.
     *
     * @return Uma lista de respostas contendo os detalhes de todas as autoavaliações.
     */
    List<AutoavaliacaoResponse> listarAvaliacoes(Long idUser);
}
