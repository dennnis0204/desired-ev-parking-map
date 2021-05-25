package com.greenparkingbook.desiredevparkingmap.repository;

import com.greenparkingbook.desiredevparkingmap.model.AuthProvider;
import com.greenparkingbook.desiredevparkingmap.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenEmail_whenFindByEmail_thenReturnUser() {
        // given
        User john = new User();
        john.setName("Bob");
        john.setEmail("bob@gmail.com");
        john.setProvider(AuthProvider.google);
        john.setChargingPoints(new ArrayList<>());
        entityManager.persist(john);
        entityManager.flush();

        //when
        Optional<User> foundOptional = userRepository.findByEmail(john.getEmail());
        User found = foundOptional.orElse(null);

        //then
        assertThat(found.getName()).isEqualTo(john.getName());
        assertThat(found.getEmail()).isEqualTo(john.getEmail());
    }
}
