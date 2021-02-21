package com.greenparkingbook.desiredevparkingmap.service;

import com.greenparkingbook.desiredevparkingmap.dto.UserDto;
import com.greenparkingbook.desiredevparkingmap.model.AuthProvider;
import com.greenparkingbook.desiredevparkingmap.model.User;
import com.greenparkingbook.desiredevparkingmap.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    String email = "john@gmail.com";
    User john = new User();
    UserDto johnDto = new UserDto();

    @BeforeEach
    public void setUp() {
        john.setName("John");
        john.setEmail("john@gmail.com");
        john.setProvider(AuthProvider.google);

        johnDto.setName("John");
        johnDto.setEmail("john@gmail.com");

        Mockito.when(userRepository.findByEmail(john.getEmail())).thenReturn(Optional.of(john));
    }

    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        User found = userService.getUserByEmail(email);
        assertThat(found.getEmail()).isEqualTo(john.getEmail());
    }

    @Test
    public void whenFindByEmail_thenReturnUserDto() {
        UserDto foundDto = userService.getUser(email);
        assertThat(foundDto.getEmail()).isEqualTo(johnDto.getEmail());
    }
}
