package com.worksafe.api.interfaces.dto.exception;

public record FieldErrorDetail(
        String field,
        String message
) {
}
