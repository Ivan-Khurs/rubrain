package com.rubrain.ivankhurs.test.service;

import com.rubrain.ivankhurs.test.model.dto.WalletDTO;

public interface WalletService {
    void recharge(long walletId, long value);

    void withdraw(long walletId, long value);

    WalletDTO getWalletById(long walletId);
}
