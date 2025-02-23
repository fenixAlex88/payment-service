package org.example.paymentservice.model.mapper;

import org.example.paymentservice.model.dto.event.OrderEventDto;
import org.example.paymentservice.model.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEventDtoToPayment {

    OrderEventDtoToPayment INSTANCE = Mappers.getMapper(OrderEventDtoToPayment.class);

    @Mapping(target = "cost", source = "totalCost")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "wallet", ignore = true)
    Payment orderEventToPayment(OrderEventDto orderEvent);

    List<Payment> orderEventListToPaymentList(List<OrderEventDto> orderEvents);
}


