package com.worksafe.api.infrastructure.api.rest;

import com.worksafe.api.interfaces.controller.AlertaController;
import com.worksafe.api.interfaces.dto.input.AlertaRequest;
import com.worksafe.api.interfaces.dto.output.AlertaDetailsResponse;
import com.worksafe.api.interfaces.dto.output.AlertaResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/alertas")
public class AlertaRestController {

    private final AlertaController alertaController;

    public AlertaRestController(AlertaController alertaController) {
        this.alertaController = alertaController;
    }

    @PostMapping
    public ResponseEntity<AlertaResponse> create(
            @Valid @RequestBody AlertaRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        final AlertaResponse response = alertaController.create(request);
        URI uri = uriBuilder.path("/alertas/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AlertaDetailsResponse>> listarTodosAlertas() {
        final List<AlertaDetailsResponse> page = alertaController.listarTodosAlertas();
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> details(@PathVariable Long id) {
        final AlertaResponse response = alertaController.findById(id);
        return ResponseEntity.ok(response);
    }

}
