package com.personal.asung_coffee_store.global.exception;

import com.personal.asung_coffee_store.global.dto.RsData;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus
    public ResponseEntity<RsData<Void>> ServiceExceptionHandle(ServiceException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(
                        new RsData<>(
                                e.getCode(),
                                e.getMsg()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus
    public ResponseEntity<RsData<Void>> ValidateExceptionHandle(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        String errorMessage = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return ResponseEntity
                .status(e.getStatusCode())
                .body(
                        new RsData<>(
                                "400-1",
                                errorMessage
                        )
                );
    }
}
