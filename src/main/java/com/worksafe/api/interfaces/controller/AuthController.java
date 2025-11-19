package com.worksafe.api.interfaces.controller;

import com.worksafe.api.interfaces.dto.input.LoginRequest;
import com.worksafe.api.interfaces.dto.output.AuthResponse;
import jakarta.validation.Valid;

public interface AuthController {
    /**
     * Authenticates a user and returns an authentication response.
     *
     * @param request the login request containing username and password
     * @return the authentication response with token and user details
     */
    AuthResponse login(@Valid LoginRequest request);
}
