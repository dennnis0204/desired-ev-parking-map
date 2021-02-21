package com.greenparkingbook.desiredevparkingmap.controller;

import com.greenparkingbook.desiredevparkingmap.dto.UserDto;
import com.greenparkingbook.desiredevparkingmap.security.UserPrincipal;
import com.greenparkingbook.desiredevparkingmap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/user")
    public UserDto getUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userService.getUser(userPrincipal.getEmail());
    }
}
