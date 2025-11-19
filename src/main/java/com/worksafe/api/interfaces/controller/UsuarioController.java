package com.worksafe.api.interfaces.controller;

import com.worksafe.api.interfaces.dto.input.UsuarioRequest;
import com.worksafe.api.interfaces.dto.output.UsuarioDetailsResponse;
import com.worksafe.api.interfaces.dto.output.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface UsuarioController {

    /**
     * Cria um novo usuário com base na requisição fornecida.
     *
     * @param request Objeto contendo os dados do usuário a ser criado.
     * @return Objeto de resposta contendo os detalhes do usuário criado.
     */
    UsuarioResponse create(@Valid UsuarioRequest request);

    /**
     * Lista todos os usuários com paginação.
     *
     * @param pageSize   Número de usuários por página.
     * @param pageNumber Número da página a ser retornada.
     * @return Página contendo os detalhes dos usuários.
     */
    Page<UsuarioDetailsResponse> listarTodosUsuarios(Integer pageSize, Integer pageNumber);

    /**
     * Obtém os detalhes de um usuário específico com base no CPF fornecido.
     *
     * @param cpf CPF do usuário a ser consultado.
     * @return Objeto de resposta contendo os detalhes do usuário.
     */
    UsuarioResponse findByCpf(String cpf);

    /**
     * Exclui um usuário com base no CPF fornecido.
     *
     * @param cpf CPF do usuário a ser excluído.
     */
    void deleteByCpf(String cpf);

    /**
     * Desativa um usuário com base no CPF fornecido.
     *
     * @param cpf CPF do usuário a ser desativado.
     */
    void deactivateByCpf(String cpf);
}
