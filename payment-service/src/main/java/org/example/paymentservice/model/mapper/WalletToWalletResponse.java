package org.example.paymentservice.model.mapper;

import org.example.paymentservice.model.dto.response.WalletResponse;
import org.example.paymentservice.model.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WalletToWalletResponse {

    WalletToWalletResponse INSTANCE = Mappers.getMapper(WalletToWalletResponse.class);

    WalletResponse toWalletResponse(Wallet wallet);
}

