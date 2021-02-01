package com.greenparkingbook.desiredevparkingmap.exception;

public class ChargingPointDoesNotExist extends RuntimeException {

    public ChargingPointDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }
    public ChargingPointDoesNotExist(String message) {
        super(message);
    }
}
