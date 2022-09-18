package com.pagamento.pix.controller.shared;

import com.pagamento.pix.application.shared.PaginateDto;
import com.pagamento.pix.controller.shared.error.ApiError;
import com.pagamento.pix.core.domain.shared.exception.DomainException;
import com.pagamento.pix.core.domain.shared.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public abstract class BaseControllerImpl<T> {

    protected ResponseEntity<T> notFoundAggregate() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<List<T>> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Void> created() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    protected ResponseEntity<T> ok(T obj) {
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    protected ResponseEntity<List<T>> ok(List<T> items) {
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    protected ResponseEntity<List<T>> ok(List<T> items, PaginateDto paginate) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("content-range", paginate.getTotal());
        responseHeaders.set("accept-range", "1000");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(items);
    }
    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleDomainException(DomainException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
}
