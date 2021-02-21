package com.greenparkingbook.desiredevparkingmap.controller;

import com.greenparkingbook.desiredevparkingmap.dto.ChargingPointDto;
import com.greenparkingbook.desiredevparkingmap.dto.UserPointsDto;
import com.greenparkingbook.desiredevparkingmap.security.UserPrincipal;
import com.greenparkingbook.desiredevparkingmap.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChargingPointRestController {

    private final UserServiceImpl userService;

    @GetMapping("/points")
    public UserPointsDto getUserPoints(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userService.getUserPoints(userPrincipal.getEmail());
    }

    @PostMapping("/points")
    public UserPointsDto addUserPoint(@RequestBody ChargingPointDto chargingPointDto, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.addUserPoint(userPrincipal.getEmail(), chargingPointDto);
        return userService.getUserPoints(userPrincipal.getEmail());
    }

    @DeleteMapping("/points")
    public UserPointsDto deleteUserPoint(@RequestBody ChargingPointDto chargingPointDto, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.deleteUserPoint(userPrincipal.getEmail(), chargingPointDto);
        return userService.getUserPoints(userPrincipal.getEmail());
    }
}
