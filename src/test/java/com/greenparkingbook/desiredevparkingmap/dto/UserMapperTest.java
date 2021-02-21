package com.greenparkingbook.desiredevparkingmap.dto;

import com.greenparkingbook.desiredevparkingmap.model.AuthProvider;
import com.greenparkingbook.desiredevparkingmap.model.ChargingPoint;
import com.greenparkingbook.desiredevparkingmap.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserMapperTest {

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private UserPointsMapper userPointsMapper = Mappers.getMapper(UserPointsMapper.class);
    private ChargingPointMapper chargingPointMapper = Mappers.getMapper(ChargingPointMapper.class);

    private User user;
    private ChargingPoint firstPoint;
    private ChargingPoint secondPoint;
    private ChargingPointDto chargingPointDto;

    @BeforeAll()
    public void init() {
        log.info("startup");
        log.info("startup");
        user = new User();
        user.setName("Jim");
        user.setEmail("jim@gmail.com");
        user.setProvider(AuthProvider.google);
        firstPoint = new ChargingPoint();
        firstPoint.setPower("3-5 kW");
        firstPoint.setTypeOfCurrent("AC");
        secondPoint = new ChargingPoint();
        secondPoint.setTypeOfCurrent("DC");
        secondPoint.setPower("6-15 kW");
        user.setChargingPoints(List.of(firstPoint, secondPoint));
        chargingPointDto = new ChargingPointDto();
        chargingPointDto.setPower("3-5 kW");
        chargingPointDto.setTypeOfCurrent("AC");
    }

    @AfterAll
    public void tearDown() {
        log.info("tearDown");
        user = null;
    }

    @Test
    public void givenUserToUserDto_whenMaps_thenCorrect() {
        UserDto userDto = userMapper.userToUserDto(user);
        assertThat(userDto.getName()).isEqualTo(user.getName());
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void givenChargingPointToChargingPointDto_whenMaps_thenCorrect() {
        ChargingPointDto chargingPointDto = userPointsMapper.chargingPointToChargingPointDto(firstPoint);
        assertThat(chargingPointDto.getPower()).isEqualTo(firstPoint.getPower());
        assertThat(chargingPointDto.getTypeOfCurrent()).isEqualTo(firstPoint.getTypeOfCurrent());
    }

    @Test
    public void givenChargingPointDtoToChargingPoint_whenMaps_thenCorrect() {
        ChargingPoint chargingPoint = chargingPointMapper.chargingPointDtoToChargingPoint(chargingPointDto);
        assertThat(chargingPoint.getPower()).isEqualTo(chargingPointDto.getPower());
        assertThat(chargingPoint.getTypeOfCurrent()).isEqualTo(chargingPointDto.getTypeOfCurrent());
    }
}