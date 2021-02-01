package com.greenparkingbook.desiredevparkingmap.dto;

import com.greenparkingbook.desiredevparkingmap.model.ChargingPoint;
import com.greenparkingbook.desiredevparkingmap.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPointsMapper {

    UserPointsDto userToUserPointsDto(User user);
    ChargingPointDto chargingPointToChargingPointDto(ChargingPoint chargingPoint);
}
