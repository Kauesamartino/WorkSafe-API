package com.worksafe.api.infrastructure.api.rest;

import com.worksafe.api.infrastructure.security.CustomUserDetails;
import com.worksafe.api.interfaces.controller.AutoavaliacaoController;
import com.worksafe.api.interfaces.dto.input.AutoavaliacaoRequest;
import com.worksafe.api.interfaces.dto.output.AutoavaliacaoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Adaptador REST para o controller de Alerta.
 * Esta classe contém as anotações específicas do springframework e
 * delega as chamadas para o controller puro.
 */
@RestController
@RequestMapping("/api/autoavaliacoes")
public class AutoavaliacaoRestController {

    private final AutoavaliacaoController autoavaliacaoController;

    public AutoavaliacaoRestController(AutoavaliacaoController autoavaliacaoController) {
        this.autoavaliacaoController = autoavaliacaoController;
    }

    @PostMapping
    public ResponseEntity<AutoavaliacaoResponse> create(@RequestBody @Valid AutoavaliacaoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        Long idUser = user.getId();

        final AutoavaliacaoResponse response = autoavaliacaoController.create(request, idUser);
        URI uri = uriComponentsBuilder.path("/autoavaliacoes/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoavaliacaoResponse> buscarPorId(@PathVariable Long id) {
        final AutoavaliacaoResponse response = autoavaliacaoController.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AutoavaliacaoResponse>> listarAvaliacoes() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        Long idUser = user.getId();

        final List<AutoavaliacaoResponse> response =
                autoavaliacaoController.listarAvaliacoes(idUser);

        return ResponseEntity.ok(response);
    }
}
