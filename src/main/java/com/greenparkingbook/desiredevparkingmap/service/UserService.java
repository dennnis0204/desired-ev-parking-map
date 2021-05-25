package com.greenparkingbook.desiredevparkingmap.service;

import com.greenparkingbook.desiredevparkingmap.dto.ChargingPointDto;
import com.greenparkingbook.desiredevparkingmap.dto.UserDto;
import com.greenparkingbook.desiredevparkingmap.dto.UserPointsDto;
import com.greenparkingbook.desiredevparkingmap.model.User;

import javax.validation.Valid;

public interface UserService {

    UserDto getUser(String email);

    UserPointsDto getUserPoints(String email);

    void addUserPoint(String email, @Valid ChargingPointDto chargingPointDto);

    void deleteUserPoint(String email, @Valid ChargingPointDto chargingPointDto);

    void updateUserPoint(String email, @Valid ChargingPointDto chargingPointDto);

    User getUserByEmail(String email);
}
