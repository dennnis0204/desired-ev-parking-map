package com.greenparkingbook.desiredevparkingmap.repository;

import com.greenparkingbook.desiredevparkingmap.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByNameStartingWithIgnoreCase(String cityName);
}
