package com.greenparkingbook.desiredevparkingmap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
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
    public void givenCityName_whenGetCity_thenReturnCityJson() throws Exception {

        mockMvc.perform(get("/api/cities?cityName=war").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].length()", is(3)))
                .andExpect(jsonPath("$.[0].latitude", is(52.23)))
                .andExpect(jsonPath("$.[0].longitude", is(21.01)))
                .andExpect(jsonPath("$.[0].name", is("Warszawa")));
    }
}
