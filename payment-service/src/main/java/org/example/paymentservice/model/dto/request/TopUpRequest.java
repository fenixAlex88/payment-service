package org.example.paymentservice.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TopUpRequest(
        @NotNull(message = "Amount is required")
        @Min(value = 0, message = "Amount must be positive")
        BigDecimal amount
) {}
