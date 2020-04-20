package com.rubrain.ivankhurs.test.model.dto;

import java.util.Objects;

public class WalletDTO {
    private long balance;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletDTO walletDTO = (WalletDTO) o;
        return balance == walletDTO.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }
}
