package com.greenparkingbook.desiredevparkingmap.exception;

public class ChargingPointDtoIsInvalid extends RuntimeException {

    public ChargingPointDtoIsInvalid(String message, Throwable cause) {
        super(message, cause);
    }

    public ChargingPointDtoIsInvalid(String message) {
        super(message);
    }
}
