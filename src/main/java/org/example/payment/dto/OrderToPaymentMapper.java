package org.example.payment.dto;

import org.example.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderToPaymentMapper {
    OrderToPaymentMapper INSTANCE = Mappers.getMapper(OrderToPaymentMapper.class);

    @Mapping(source = "totalCost", target = "cost")
    @Mapping(source = "orderId", target = "orderId")
    @Mapping(target = "wallet", ignore = true)
    Payment orderEventToPayment(OrderEvent orderEvent);
}

