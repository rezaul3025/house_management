package com.xyz.house_management.controller;

import com.xyz.house_management.config.TestContainerConfig;
import com.xyz.house_management.data.HouseTestFixture;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseRequestValidationTest extends TestContainerConfig{
    private final String HOUSES_URL = "/api/v1/houses";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void housesTitleValidationTest() throws Exception {
        this.mockMvc.perform(post(HOUSES_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(HouseTestFixture.HOUSE_DATA_INVALID_DESC))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is("Description cannot be empty or null")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void housesDescriptionValidationTest() throws Exception {
        this.mockMvc.perform(post(HOUSES_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(HouseTestFixture.HOUSE_DATA_INVALID_TITLE))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Title cannot be empty or null")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void housesLocationValidationTest() throws Exception {
        this.mockMvc.perform(post(HOUSES_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(HouseTestFixture.HOUSE_DATA_INVALID_LOCATION))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", Is.is("Location cannot be empty or null")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void housesConstructionYearValidationTest() throws Exception {
        this.mockMvc.perform(post(HOUSES_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(HouseTestFixture.HOUSE_DATA_INVALID_CONS_YEAR))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.constructionYear", Is.is("Construction year cannot be null")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void housesNumberOfFloorValidationTest() throws Exception {
        this.mockMvc.perform(post(HOUSES_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(HouseTestFixture.HOUSE_DATA_INVALID_NO_OF_FLOOR))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfFloor", Is.is("Number of  floor cannot be null")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void housesSizeValidationTest() throws Exception {
        this.mockMvc.perform(post(HOUSES_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(HouseTestFixture.HOUSE_DATA_INVALID_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalSizeInSqm", Is.is("Total size in squire cannot be null")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
