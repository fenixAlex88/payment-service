package org.example.payment.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderToInventoryMapper {
    OrderToInventoryMapper INSTANCE = Mappers.getMapper(OrderToInventoryMapper.class);

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "items", target = "items")
    @Mapping(source = "deliveryAddress", target = "deliveryAddress")
    InventoryEvent orderEventToInventoryEvent(OrderEvent order);
}

