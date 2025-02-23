package org.example.paymentservice.model.dto.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderEventDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotEmpty(message = "Order details cannot be empty")
    private List<OrderDetailsDto> orderDetails;

    @NotNull(message = "Total cost is required")
    @Positive(message = "Total cost must be positive")
    private BigDecimal totalCost;

    @NotEmpty(message = "Destination address is required")
    private String destinationAddress;
}

