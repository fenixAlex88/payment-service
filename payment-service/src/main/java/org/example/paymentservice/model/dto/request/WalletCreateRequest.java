package org.example.paymentservice.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record WalletCreateRequest(
        @NotNull(message = "User ID is required")
        Long userId
) {}
