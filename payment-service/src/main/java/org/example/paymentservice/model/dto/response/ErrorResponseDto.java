package org.example.paymentservice.model.dto.response;


public record ErrorResponseDto(
        String message,
        int errorCode,
        String details
) {}
