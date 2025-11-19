package com.worksafe.api.interfaces.dto.input;

public record LoginRequest(
        String username,
        String password
) {
}
