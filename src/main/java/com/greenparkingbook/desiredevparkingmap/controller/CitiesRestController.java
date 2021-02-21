package com.greenparkingbook.desiredevparkingmap.controller;

import com.greenparkingbook.desiredevparkingmap.dto.CityDto;
import com.greenparkingbook.desiredevparkingmap.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CitiesRestController {

    private final CityService cityService;

    @GetMapping("/cities")
    List<CityDto> getCities(@RequestParam String cityName) {

        return cityService.getCities(cityName);
    }
}
