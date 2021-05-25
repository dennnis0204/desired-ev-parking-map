package com.greenparkingbook.desiredevparkingmap.repository;

import com.greenparkingbook.desiredevparkingmap.model.ChargingPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargingPointRepository extends JpaRepository<ChargingPoint, Long> {
    List<ChargingPoint> findAllByUser_Email(String email);
    Boolean existsChargingPointById(Long id);
}
