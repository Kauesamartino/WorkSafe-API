package com.worksafe.api.infrastructure.api.rest;

import com.worksafe.api.interfaces.controller.RecomendacaoController;
import com.worksafe.api.interfaces.dto.input.RecomendacaoRequest;
import com.worksafe.api.interfaces.dto.output.RecomendacaoResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacaoRestController {

    private final RecomendacaoController recomendacaoController;

    public RecomendacaoRestController(RecomendacaoController recomendacaoController) {
        this.recomendacaoController = recomendacaoController;
    }

    @PostMapping
    public ResponseEntity<RecomendacaoResponse> create(@RequestBody @Valid RecomendacaoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        final RecomendacaoResponse response = recomendacaoController.create(request);
        URI uri = uriComponentsBuilder.path("/recomendacoes/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacaoResponse> buscarPorId(Long id) {
        final RecomendacaoResponse response = recomendacaoController.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RecomendacaoResponse>> listarTodasRecomendacoes() {
        final List<RecomendacaoResponse> response = recomendacaoController.listarTodasRecomendacoes();
        return ResponseEntity.ok(response);
    }
}
