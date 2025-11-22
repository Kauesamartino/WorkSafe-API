package com.worksafe.api.infrastructure.api.rest;

import com.worksafe.api.interfaces.controller.WearableDataController;
import com.worksafe.api.interfaces.dto.input.WearableRequest;
import com.worksafe.api.interfaces.dto.output.WearableResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para gerenciar dados de dispositivos vestíveis (wearables).
 * Atualmente, esta classe está vazia e serve como um placeholder para futuras implementações.
 */
@RestController
@RequestMapping("/api/wearable-data")
public class WearableDataRestController {

    private final WearableDataController wearableDataController;

    public WearableDataRestController(WearableDataController wearableDataController) {
        this.wearableDataController = wearableDataController;
    }

    @PostMapping
    public ResponseEntity<WearableResponse> create(WearableRequest request, UriComponentsBuilder uriComponentsBuilder) {
        final WearableResponse response = wearableDataController.create(request);
        URI uri = uriComponentsBuilder.path("/wearable-data/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WearableResponse> buscarPorId(@PathVariable Long id) {
        final WearableResponse response = wearableDataController.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WearableResponse>> listarDados() {
        final List<WearableResponse> response = wearableDataController.listarDados();
        return ResponseEntity.ok(response);
    }
}
