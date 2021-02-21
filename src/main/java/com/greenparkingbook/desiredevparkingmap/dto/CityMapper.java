package com.greenparkingbook.desiredevparkingmap.dto;

import com.greenparkingbook.desiredevparkingmap.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDto cityToCityDto(City city);
}
