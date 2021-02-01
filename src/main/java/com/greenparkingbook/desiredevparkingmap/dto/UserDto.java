package com.greenparkingbook.desiredevparkingmap.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Data
public class UserDto {

    @Email
    private String email;
    private String name;
}
