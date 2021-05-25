package com.greenparkingbook.desiredevparkingmap.exception;

public class UserMaxPointsNumberExceededException extends RuntimeException {

    public UserMaxPointsNumberExceededException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserMaxPointsNumberExceededException(String message) {
        super(message);
    }
}
