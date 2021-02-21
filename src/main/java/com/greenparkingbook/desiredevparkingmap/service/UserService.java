package com.greenparkingbook.desiredevparkingmap.service;

import com.greenparkingbook.desiredevparkingmap.dto.ChargingPointDto;
import com.greenparkingbook.desiredevparkingmap.dto.UserDto;
import com.greenparkingbook.desiredevparkingmap.dto.UserPointsDto;
import com.greenparkingbook.desiredevparkingmap.model.User;

public interface UserService {

    UserDto getUser(String email);

    UserPointsDto getUserPoints(String email);

    void addUserPoint(String email, ChargingPointDto chargingPointDto);

    void deleteUserPoint(String email, ChargingPointDto chargingPointDto);

    void updateUserPoint(String email, ChargingPointDto chargingPointDto);

    User getUserByEmail(String email);
}
