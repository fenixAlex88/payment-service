package org.example.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InventoryEvent {
    private Long orderId;
    private Long userId;
    private List<String> items;
    private String deliveryAddress;
}
