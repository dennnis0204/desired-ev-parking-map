package com.greenparkingbook.desiredevparkingmap.service;

import com.greenparkingbook.desiredevparkingmap.dto.*;
import com.greenparkingbook.desiredevparkingmap.exception.ChargingPointDoesNotExist;
import com.greenparkingbook.desiredevparkingmap.exception.UserMaxPointsNumberExceeded;
import com.greenparkingbook.desiredevparkingmap.model.ChargingPoint;
import com.greenparkingbook.desiredevparkingmap.model.User;
import com.greenparkingbook.desiredevparkingmap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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
    public void addUserPoint(String email, ChargingPointDto chargingPointDto) {
        User user = getUserByEmail(email);
        ChargingPoint chargingPoint = mapValidatePoint(chargingPointDto);

        if (user.getChargingPoints().size() < userMaxPointsNumber) {
            user.addChargingPoint(chargingPoint);
            userRepository.save(user);
        } else {
            throw new UserMaxPointsNumberExceeded(String.format("User already has %s charging points on the map!", userMaxPointsNumber));
        }
    }

    @Override
    public void deleteUserPoint(String email, ChargingPointDto chargingPointDto) {
        User user = getUserByEmail(email);
        ChargingPoint chargingPoint = mapValidatePoint(chargingPointDto);
        Optional<ChargingPoint> chargingPointById = user.findChargingPointById(chargingPoint.getId());
        if (chargingPointById.isPresent() && chargingPointById.get().equals(chargingPoint)) {
            user.removeChargingPoint(chargingPoint);
            userRepository.save(user);
        } else {
            throw new ChargingPointDoesNotExist("User has no such point!");
        }
    }

    @Override
    public void updateUserPoint(String email, ChargingPointDto chargingPointDto) {
        User user = getUserByEmail(email);
        ChargingPoint chargingPoint = mapValidatePoint(chargingPointDto);
        if (user.findChargingPointById(chargingPoint.getId()).isPresent()) {
            user.updateChargingPoint(chargingPoint);
            userRepository.save(user);
        } else {
            throw new ChargingPointDoesNotExist(
                    String.format("User has no point with id = %s!", chargingPoint.getId()));
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
    }


    private ChargingPoint mapValidatePoint(ChargingPointDto chargingPointDto) {
        ChargingPoint chargingPoint = chargingPointMapper.chargingPointDtoToChargingPoint(chargingPointDto);
        Set<ConstraintViolation<ChargingPoint>> constraintViolations = validator.validate(chargingPoint);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
        return chargingPoint;
    }
}
