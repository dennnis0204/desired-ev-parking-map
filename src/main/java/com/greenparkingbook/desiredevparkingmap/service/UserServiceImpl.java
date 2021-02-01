package com.greenparkingbook.desiredevparkingmap.service;

import com.greenparkingbook.desiredevparkingmap.dto.*;
import com.greenparkingbook.desiredevparkingmap.exception.ChargingPointDoesNotExist;
import com.greenparkingbook.desiredevparkingmap.exception.UserMaxPointsNumberExceeded;
import com.greenparkingbook.desiredevparkingmap.model.ChargingPoint;
import com.greenparkingbook.desiredevparkingmap.model.User;
import com.greenparkingbook.desiredevparkingmap.repository.ChargingPointRepository;
import com.greenparkingbook.desiredevparkingmap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Value("${settings.user.maxPointsNumber}")
    private Integer userMaxPointsNumber;

    private final UserRepository userRepository;
    private final ChargingPointRepository chargingPointRepository;
    private final UserMapper userMapper;
    private final UserPointsMapper userPointsMapper;
    private final ChargingPointMapper chargingPointMapper;
    private final EntityManager entityManager;
    private final Validator validator;


    public UserServiceImpl(UserRepository userRepository, ChargingPointRepository chargingPointRepository, UserMapper userMapper, UserPointsMapper userPointsMapper, ChargingPointMapper chargingPointMapper, EntityManager entityManager, Validator validator) {
        this.userRepository = userRepository;
        this.chargingPointRepository = chargingPointRepository;
        this.userMapper = userMapper;
        this.userPointsMapper = userPointsMapper;
        this.chargingPointMapper = chargingPointMapper;
        this.entityManager = entityManager;
        this.validator = validator;
    }

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
    @Transactional
    public void addUserPoint(String email, ChargingPointDto chargingPointDto) {
        User user = getUserByEmail(email);
        ChargingPoint chargingPoint = chargingPointMapper.chargingPointDtoToChargingPoint(chargingPointDto);

        if (user.getChargingPoints().size() < userMaxPointsNumber) {
            user.addChargingPoint(chargingPoint);
            userRepository.save(user);
            entityManager.refresh(user);
        } else {
            throw new UserMaxPointsNumberExceeded(String.format("User already has %s charging points on the map!", userMaxPointsNumber));
        }
    }

    @Override
    public void deleteUserPoint(String email, ChargingPointDto chargingPointDto) {
        User user = getUserByEmail(email);
        ChargingPoint chargingPoint = chargingPointMapper.chargingPointDtoToChargingPoint(chargingPointDto);
        Set<ConstraintViolation<ChargingPoint>> constraintViolations = validator.validate(chargingPoint);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
        if (chargingPointRepository.existsChargingPointById(chargingPoint.getId())) {
            user.removeChargingPoint(chargingPoint);
            userRepository.save(user);
        } else {
            throw new ChargingPointDoesNotExist(String.format("Point with id %d does not exist on the map", chargingPoint.getId()));
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
    }
}
