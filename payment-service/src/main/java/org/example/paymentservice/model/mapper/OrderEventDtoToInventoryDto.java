package org.example.paymentservice.model.mapper;

import org.example.paymentservice.model.dto.event.InventoryEventDto;
import org.example.paymentservice.model.dto.event.OrderEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEventDtoToInventoryDto {

    OrderEventDtoToInventoryDto INSTANCE = Mappers.getMapper(OrderEventDtoToInventoryDto.class);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "orderDetails", source = "orderDetails")
    @Mapping(target = "destinationAddress", source = "destinationAddress")
    InventoryEventDto orderEventToInventoryEvent(OrderEventDto orderEvent);

    List<InventoryEventDto> orderEventListToInventoryEventList(List<OrderEventDto> orderEvents);

}
