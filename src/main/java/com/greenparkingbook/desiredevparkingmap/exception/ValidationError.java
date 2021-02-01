package com.greenparkingbook.desiredevparkingmap.exception;

import lombok.Data;

@Data
public class ValidationError {
    private String path;
    private String message;
}
