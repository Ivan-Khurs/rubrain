package com.rubrain.ivankhurs.test.service.exception;

public class WalletServiceException extends RuntimeException {
    public WalletServiceException() {
        super();
    }

    public WalletServiceException(String message) {
        super(message);
    }

    public WalletServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletServiceException(Throwable cause) {
        super(cause);
    }
}
