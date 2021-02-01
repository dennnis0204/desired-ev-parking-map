package com.greenparkingbook.desiredevparkingmap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(UserMaxPointsNumberExceeded.class)
    public ResponseEntity<String> handleUserMaxPointsNumberExceeded() {
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(ChargingPointDoesNotExist.class)
    public ResponseEntity<String> handleChargingPointDoesNotExist(ChargingPointDoesNotExist ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ValidationError>> handleValidationExceptions(ConstraintViolationException ex) {
        List<ValidationError> errors = ex.getConstraintViolations().stream()
                .map(this::toValidationError)
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private ValidationError toValidationError(ConstraintViolation constraintViolation) {
        ValidationError error = new ValidationError();
        error.setPath(constraintViolation.getPropertyPath().toString());
        error.setMessage(constraintViolation.getMessage());
        return error;
    }
}
