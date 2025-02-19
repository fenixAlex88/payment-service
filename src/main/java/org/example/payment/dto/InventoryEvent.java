package org.example.payment.dto;

import lombok.Data;

import java.util.List;

@Data
public class InventoryEvent {
    private Long orderId;
    private Long userId;
    private List<String> items;
    private String deliveryAddress;
}
