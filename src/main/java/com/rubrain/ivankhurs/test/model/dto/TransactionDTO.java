package com.rubrain.ivankhurs.test.model.dto;

import java.util.Objects;

public class TransactionDTO {
    private long walletId;
    private String action;
    private long value;

    public TransactionDTO(long walletId, String action, long value) {
        this.walletId = walletId;
        this.action = action;
        this.value = value;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return walletId == that.walletId &&
                value == that.value &&
                Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletId, action, value);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "walletId=" + walletId +
                ", action='" + action + '\'' +
                ", value=" + value +
                '}';
    }
}
