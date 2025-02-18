package org.example.payment.config;

import org.example.payment.dto.OrderToInventoryMapper;
import org.example.payment.dto.OrderToPaymentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public OrderToInventoryMapper orderMapper() {
        return OrderToInventoryMapper.INSTANCE;
    }

    @Bean
    public OrderToPaymentMapper orderToPaymentMapper() { return OrderToPaymentMapper.INSTANCE; }
}
