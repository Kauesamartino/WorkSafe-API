package com.worksafe.api.infrastructure.api.rest;

import com.worksafe.api.interfaces.controller.AuthController;
import com.worksafe.api.interfaces.dto.input.LoginRequest;
import com.worksafe.api.interfaces.dto.output.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthController authController;

    public AuthRestController(AuthController authController) {
        this.authController = authController;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        final AuthResponse response = authController.login(request);
        return ResponseEntity.ok(response);
    }

}
