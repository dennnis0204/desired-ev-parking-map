package com.greenparkingbook.desiredevparkingmap.exception;

public class UserMaxPointsNumberExceeded extends RuntimeException {

    public UserMaxPointsNumberExceeded(String message, Throwable cause) {
        super(message, cause);
    }
    public UserMaxPointsNumberExceeded(String message) {
        super(message);
    }
}
