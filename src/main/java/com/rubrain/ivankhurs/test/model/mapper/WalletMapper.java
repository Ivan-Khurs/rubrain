package com.rubrain.ivankhurs.test.model.mapper;

import com.rubrain.ivankhurs.test.model.dto.WalletDTO;
import com.rubrain.ivankhurs.test.model.entity.Wallet;
import org.mapstruct.Mapper;

/**
 * The WalletMapper provides auto mapping between DTOs and Entities
 */
@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDTO toDTO (Wallet wallet);
    Wallet fromDTO (WalletDTO walletDTO);
}
