package com.example.internet_market_backend.exceptions_handler;

import com.example.internet_market_backend.exceptions.DataNotFoundException;
import com.example.internet_market_backend.exceptions.EmptyFileException;
import com.example.internet_market_backend.exceptions.OperationFailedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<Object> handleOperationFailedException(OperationFailedException ex) {
        ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<Object> handleEmptyFileException(EmptyFileException ex) {
        ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
