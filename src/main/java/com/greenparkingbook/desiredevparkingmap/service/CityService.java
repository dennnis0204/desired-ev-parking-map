package com.greenparkingbook.desiredevparkingmap.service;

import com.greenparkingbook.desiredevparkingmap.dto.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> getCities(String cityName);
}
