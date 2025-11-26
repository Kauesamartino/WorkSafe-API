package com.worksafe.api.interfaces.controller;

import com.worksafe.api.interfaces.dto.input.RecomendacaoRequest;
import com.worksafe.api.interfaces.dto.output.RecomendacaoResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface RecomendacaoController {
    /**
     * Cria uma nova recomendação com base na requisição fornecida.
     *
     * @param request A requisição contendo os dados da recomendação a ser criada.
     * @return A resposta contendo os detalhes da recomendação criada.
     */
    RecomendacaoResponse create(@Valid RecomendacaoRequest request, Long idUser);

    /**
     * Busca uma recomendação pelo seu ID.
     *
     * @param id O ID da recomendação a ser buscada.
     * @return A resposta contendo os detalhes da recomendação encontrada.
     */
    RecomendacaoResponse buscarPorId(Long id);

    /**
     * Lista todas as recomendações disponíveis.
     *
     * @return Uma lista de respostas contendo os detalhes de todas as recomendações.
     */
    List<RecomendacaoResponse> listarTodasRecomendacoes(Long idUser);
}
