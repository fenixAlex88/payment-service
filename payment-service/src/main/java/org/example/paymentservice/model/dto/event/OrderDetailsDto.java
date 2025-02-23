package org.example.paymentservice.model.dto.event;

import lombok.Data;

@Data
public class OrderDetailsDto {
    private Long productId;
    private Integer count;
}