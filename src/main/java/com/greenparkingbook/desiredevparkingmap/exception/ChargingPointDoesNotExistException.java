package com.greenparkingbook.desiredevparkingmap.exception;

public class ChargingPointDoesNotExistException extends RuntimeException {

    public ChargingPointDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    public ChargingPointDoesNotExistException(String message) {
        super(message);
    }
}
