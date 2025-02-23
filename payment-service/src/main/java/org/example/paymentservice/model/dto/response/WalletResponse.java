package org.example.paymentservice.model.dto.response;

import java.math.BigDecimal;

public record WalletResponse(
        Long id,
        BigDecimal balance
) {}
