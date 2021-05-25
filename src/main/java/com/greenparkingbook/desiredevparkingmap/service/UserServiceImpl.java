package com.greenparkingbook.desiredevparkingmap.service;

import com.greenparkingbook.desiredevparkingmap.dto.*;
import com.greenparkingbook.desiredevparkingmap.exception.ChargingPointDoesNotExistException;
import com.greenparkingbook.desiredevparkingmap.exception.UserMaxPointsNumberExceededException;
import com.greenparkingbook.desiredevparkingmap.model.ChargingPoint;
import com.greenparkingbook.desiredevparkingmap.model.User;
import com.greenparkingbook.desiredevparkingmap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_MAX_POINTS_NUMBER_EXCEEDED_MESSAGE = "User already has %s charging points on the map!";
    private static final String USER_HAS_NO_SUCH_POINT_MESSAGE = "User has no such point!";
    private static final String USER_NOT_FOUND_MESSAGE = "User not found with email : ";

    @Value("${settings.user.maxPointsNumber}")
    private Integer userMaxPointsNumber;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPointsMapper userPointsMapper;
    private final ChargingPointMapper chargingPointMapper;
    private final Validator validator;

    @Override
    public UserDto getUser(String email) {
        User user = getUserByEmail(email);
        UserDto userDto = userMapper.userToUserDto(user);
        return userDto;
    }

    @Override
    public UserPointsDto getUserPoints(String email) {
        User user = getUserByEmail(email);
        UserPointsDto userPointsDto = userPointsMapper.userToUserPointsDto(user);
        return userPointsDto;
    }

    @Override
    public void addUserPoint(String email, @Valid ChargingPointDto chargingPointDto) {
        User user = getUserByEmail(email);
        ChargingPoint newChargingPoint = chargingPointMapper.chargingPointDtoToChargingPoint(chargingPointDto);
        validateChargingPoint(newChargingPoint);

        if (user.getChargingPoints().size() < userMaxPointsNumber) {
            user.addChargingPoint(newChargingPoint);
            userRepository.save(user);
        } else {
            throw new UserMaxPointsNumberExceededException(
                    String.format(USER_MAX_POINTS_NUMBER_EXCEEDED_MESSAGE, userMaxPointsNumber));
        }
    }


    @Override
    public void deleteUserPoint(String email, @Valid ChargingPointDto chargingPointDto) {
        User user = getUserByEmail(email);
        ChargingPoint newChargingPoint = chargingPointMapper.chargingPointDtoToChargingPoint(chargingPointDto);
        validateChargingPoint(newChargingPoint);

        if (user.hasChargingPoint(newChargingPoint)) {
            user.removeChargingPoint(newChargingPoint);
            userRepository.save(user);
        } else {
            throw new ChargingPointDoesNotExistException(USER_HAS_NO_SUCH_POINT_MESSAGE);
        }
    }

    @Override
    public void updateUserPoint(String email, @Valid ChargingPointDto chargingPointDto) {
        User user = getUserByEmail(email);
        ChargingPoint chargingPoint = chargingPointMapper.chargingPointDtoToChargingPoint(chargingPointDto);
        validateChargingPoint(chargingPoint);
        if (user.hasChargingPoint(chargingPoint)) {
            user.updateChargingPoint(chargingPoint);
            userRepository.save(user);
        } else {
            throw new ChargingPointDoesNotExistException(USER_HAS_NO_SUCH_POINT_MESSAGE);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE + email));
    }

    private void validateChargingPoint(ChargingPoint newChargingPoint) {
        Set<ConstraintViolation<ChargingPoint>> constraintViolations = validator.validate(newChargingPoint);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
