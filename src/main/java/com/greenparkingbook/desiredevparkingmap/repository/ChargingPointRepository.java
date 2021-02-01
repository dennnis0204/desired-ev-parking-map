package com.greenparkingbook.desiredevparkingmap.repository;

import com.greenparkingbook.desiredevparkingmap.model.ChargingPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChargingPointRepository extends JpaRepository<ChargingPoint, Long> {
    List<ChargingPoint> findAllByUser_Email(String email);
    Boolean existsChargingPointById(Long id);
}
