package com.greenparkingbook.desiredevparkingmap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CitiesRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenUser_whenGetUser_thenReturnUserJson() throws Exception {

        mockMvc.perform(get("/api/cities?cityName=warszawa").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].length()", is(3)))
                .andExpect(jsonPath("$.[0].latitude", is(52.23)))
                .andExpect(jsonPath("$.[0].longitude", is(21.01)))
                .andExpect(jsonPath("$.[0].name", is("Warszawa")));
    }
}
