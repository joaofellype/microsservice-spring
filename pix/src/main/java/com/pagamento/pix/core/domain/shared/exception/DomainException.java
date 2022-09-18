package com.pagamento.pix.core.domain.shared.exception;

import java.util.ArrayList;
import java.util.List;

public class DomainException extends RuntimeException {
    private final List<String> errorMessages;

    public DomainException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public DomainException(String errorMessage) {
        this.errorMessages = new ArrayList<>();
        this.errorMessages.add(errorMessage);

    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
