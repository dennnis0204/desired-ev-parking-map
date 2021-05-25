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

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapperTest {

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

        user = new User();
        user.setName("Jim");
        user.setEmail("jim@gmail.com");
        user.setProvider(AuthProvider.google);

        firstPoint = new ChargingPoint();
        firstPoint.setId(1L);
        firstPoint.setPower("3-5 kW");
        firstPoint.setTypeOfCurrent("AC");
        firstPoint.setLatitude(new BigDecimal("33.33"));
        firstPoint.setLongitude(new BigDecimal("44.44"));

        secondPoint = new ChargingPoint();
        secondPoint.setId(2L);
        secondPoint.setTypeOfCurrent("DC");
        secondPoint.setPower("6-15 kW");
        secondPoint.setLatitude(new BigDecimal("88.88"));
        secondPoint.setLongitude(new BigDecimal("77.77"));

        user.setChargingPoints(List.of(firstPoint, secondPoint));

        chargingPointDto = new ChargingPointDto();
        chargingPointDto.setId(1L);
        chargingPointDto.setPower("3-5 kW");
        chargingPointDto.setTypeOfCurrent("AC");
        chargingPointDto.setLatitude(new BigDecimal("33.33"));
        chargingPointDto.setLongitude(new BigDecimal("44.44"));

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
        assertThat(firstPoint).isEqualTo(chargingPoint);
    }
}