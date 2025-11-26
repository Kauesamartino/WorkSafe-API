package com.worksafe.api.infrastructure.api.rest;

import com.worksafe.api.infrastructure.security.CustomUserDetails;
import com.worksafe.api.interfaces.controller.RecomendacaoController;
import com.worksafe.api.interfaces.dto.input.RecomendacaoRequest;
import com.worksafe.api.interfaces.dto.output.RecomendacaoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/recomendacoes")
public class RecomendacaoRestController {

    private final RecomendacaoController recomendacaoController;

    public RecomendacaoRestController(RecomendacaoController recomendacaoController) {
        this.recomendacaoController = recomendacaoController;
    }

    @PostMapping
    public ResponseEntity<RecomendacaoResponse> create(@RequestBody @Valid RecomendacaoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        Long idUser = user.getId();
        final RecomendacaoResponse response = recomendacaoController.create(request, idUser);
        URI uri = uriComponentsBuilder.path("/recomendacoes/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoResponse> buscarPorId(@PathVariable Long id) {
        final RecomendacaoResponse response = recomendacaoController.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RecomendacaoResponse>> listarTodasRecomendacoes() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        Long idUser = user.getId();
        final List<RecomendacaoResponse> response = recomendacaoController.listarTodasRecomendacoes(idUser);
        return ResponseEntity.ok(response);
    }
}
