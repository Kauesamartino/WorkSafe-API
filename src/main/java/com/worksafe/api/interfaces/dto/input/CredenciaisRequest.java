package com.worksafe.api.interfaces.dto.input;

public record CredenciaisRequest(
        String username,
        String password
) {
}
