package com.pagamento.pix.core.domain.shared.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {

    }
}
