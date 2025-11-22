package com.worksafe.api.infrastructure.api.rest;

import com.worksafe.api.interfaces.controller.UsuarioController;
import com.worksafe.api.interfaces.dto.input.UsuarioRequest;
import com.worksafe.api.interfaces.dto.output.UsuarioDetailsResponse;
import com.worksafe.api.interfaces.dto.output.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Adaptador REST para o controller de Usuario.
 * Esta classe contém as anotações específicas do springframework e
 * delega as chamadas para o controller puro.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    private final UsuarioController usuarioController;

    public UsuarioRestController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest request, UriComponentsBuilder uriComponentsBuilder) {
        final UsuarioResponse response = usuarioController.create(request);
        URI uri = uriComponentsBuilder.path("/usuarios/{cpf}").buildAndExpand(response.cpf()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDetailsResponse>> listarTodosUsuarios(
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber
    ){
        final Page<UsuarioDetailsResponse> page = usuarioController.listarTodosUsuarios(pageSize, pageNumber);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioResponse> details(@PathVariable String cpf) {
        final UsuarioResponse response = usuarioController.findByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String cpf) {
        usuarioController.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Void> deactivateUsuario(@PathVariable String cpf) {
        usuarioController.deactivateByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
