package com.greenparkingbook.desiredevparkingmap.dto;

import com.greenparkingbook.desiredevparkingmap.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
}
