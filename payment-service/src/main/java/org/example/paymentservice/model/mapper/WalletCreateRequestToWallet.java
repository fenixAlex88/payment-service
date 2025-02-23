package org.example.paymentservice.model.mapper;

import org.example.paymentservice.model.dto.request.WalletCreateRequest;
import org.example.paymentservice.model.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WalletCreateRequestToWallet {

    WalletCreateRequestToWallet INSTANCE = Mappers.getMapper(WalletCreateRequestToWallet.class);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "balance", constant = "0")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "payments", ignore = true)
    Wallet toWallet(WalletCreateRequest request);
}

