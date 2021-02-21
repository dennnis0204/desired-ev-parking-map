package com.greenparkingbook.desiredevparkingmap.repository;

import com.greenparkingbook.desiredevparkingmap.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByNameStartingWithIgnoreCase(String cityName);
}
