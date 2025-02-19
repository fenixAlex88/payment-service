package org.example.payment.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private List<String> items;
    private BigDecimal totalCost;
    private String deliveryAddress;
}
