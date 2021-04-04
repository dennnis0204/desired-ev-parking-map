package com.greenparkingbook.desiredevparkingmap.controller;

import com.greenparkingbook.desiredevparkingmap.dto.ChargingPointDto;
import com.greenparkingbook.desiredevparkingmap.dto.UserPointsDto;
import com.greenparkingbook.desiredevparkingmap.exception.ChargingPointDtoIsInvalid;
import com.greenparkingbook.desiredevparkingmap.security.UserPrincipal;
import com.greenparkingbook.desiredevparkingmap.service.UserServiceImpl;
import com.greenparkingbook.desiredevparkingmap.validator.ChargingPointDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChargingPointRestController {

    private final UserServiceImpl userService;
    private final ChargingPointDtoValidator chargingPointDtoValidator;
    private final MessageSource messageSource;

    @GetMapping("/points")
    public UserPointsDto getUserPoints(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userService.getUserPoints(userPrincipal.getEmail());
    }

    @PostMapping("/points")
    public UserPointsDto addUserPoint(
            @RequestBody @Valid ChargingPointDto chargingPointDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        System.out.println(chargingPointDto);

        chargingPointDtoValidator.validate(chargingPointDto, bindingResult);

        List<String> collect = bindingResult.getFieldErrors().stream()
                .map(fieldError -> messageSource.getMessage(fieldError.getCode(), null, Locale.ENGLISH))
                .collect(Collectors.toList());

        if (bindingResult.hasErrors()) {
            throw new ChargingPointDtoIsInvalid(collect.toString());
        }
        userService.addUserPoint(userPrincipal.getEmail(), chargingPointDto);
        return userService.getUserPoints(userPrincipal.getEmail());
    }

    @DeleteMapping("/points")
    public UserPointsDto deleteUserPoint(@RequestBody ChargingPointDto chargingPointDto, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.deleteUserPoint(userPrincipal.getEmail(), chargingPointDto);
        return userService.getUserPoints(userPrincipal.getEmail());
    }

    @PutMapping("/points")
    public UserPointsDto updateUserPoint(@RequestBody ChargingPointDto chargingPointDto, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.updateUserPoint(userPrincipal.getEmail(), chargingPointDto);
        return userService.getUserPoints(userPrincipal.getEmail());
    }
}
