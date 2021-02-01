package com.greenparkingbook.desiredevparkingmap.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPointsDto {

    private String name;
    private String email;
    private List<ChargingPointDto> chargingPoints;
}
