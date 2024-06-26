package com.example.keycloakspringboot.exception.handler;

import com.example.keycloakspringboot.dto.ApiErrorResponse;
import com.example.keycloakspringboot.exception.NotFoundException;
import com.example.keycloakspringboot.exception.ServiceException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "The request was not validated");
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorResponse.addField(fieldError.getField(), fieldError.getDefaultMessage()));
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        log.warn(e.getMessage());
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "The request was not validated");
        e.getConstraintViolations().forEach(constraintViolation -> errorResponse.addField(
                constraintViolation.getPropertyPath().toString(),
                constraintViolation.getMessage()));
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public ApiErrorResponse handleServiceException(ServiceException e) {
        String message = e.getMessage();
        log.warn(message);
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiErrorResponse handleNotFoundException(NotFoundException e) {
        String message = e.getMessage();
        log.warn(message);
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ApiErrorResponse handleRuntimeException(RuntimeException e) {
        String message = e.getMessage();
        log.warn(message);
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, message);
    }
}
