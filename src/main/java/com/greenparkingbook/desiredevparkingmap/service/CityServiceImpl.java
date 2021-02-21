package com.greenparkingbook.desiredevparkingmap.service;

import com.greenparkingbook.desiredevparkingmap.dto.CityDto;
import com.greenparkingbook.desiredevparkingmap.dto.CityMapper;
import com.greenparkingbook.desiredevparkingmap.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDto> getCities(String cityName) {
        if (cityName == null || cityName.length() < 3) {
            return Collections.emptyList();
        } else {
            return cityRepository.findAllByNameStartingWithIgnoreCase(cityName).stream()
                    .map(city -> cityMapper.cityToCityDto(city))
                    .collect(Collectors.toList());
        }
    }
}
