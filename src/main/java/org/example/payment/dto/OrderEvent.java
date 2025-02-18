package org.example.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private List<String> items;
    private BigDecimal totalCost;
    private String deliveryAddress;
}
