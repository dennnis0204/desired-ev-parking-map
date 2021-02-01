package com.greenparkingbook.desiredevparkingmap.dto;

import lombok.*;

@Data
public class ChargingPointDto {

    private Long id;
    private String typeOfCurrent;
    private String power;
    private Float latitude;
    private Float longitude;
}
