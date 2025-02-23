package org.example.paymentservice.model.dto.event;

import lombok.Data;

import java.util.List;

@Data
public class InventoryEventDto {
    private Long userId;
    private Long orderId;
    private List<OrderDetailsDto> orderDetails;
    private String destinationAddress;;
}
