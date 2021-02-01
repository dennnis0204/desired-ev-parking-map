package com.greenparkingbook.desiredevparkingmap.dto;

import com.greenparkingbook.desiredevparkingmap.model.ChargingPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChargingPointMapper {

    @Mapping(target = "user", ignore = true)
    ChargingPoint chargingPointDtoToChargingPoint(ChargingPointDto chargingPointDto);
}
