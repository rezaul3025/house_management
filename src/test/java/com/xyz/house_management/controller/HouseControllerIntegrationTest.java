package com.xyz.house_management.controller;

import com.xyz.house_management.config.TestContainerConfig;
import com.xyz.house_management.data.HouseTestFixture;
import com.xyz.house_management.persistence.entity.House;
import com.xyz.house_management.persistence.repository.HouseRepository;
import lombok.val;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseControllerIntegrationTest extends TestContainerConfig {

    private final String HOUSES_URL = "/api/v1/houses";

    private final String HOUSE_BY_ID_URL = "/api/v1/houses/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HouseRepository houseRepository;

    @AfterEach
    public void cleanup() {
        this.houseRepository.deleteAll();
    }

    @Test
    public void createHousesTest() throws Exception {
        this.mockMvc.perform(post(HOUSES_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(HouseTestFixture.HOUSE_DATA))
                .andExpect(status().isCreated());
    }

    @Test
    public void findHouseByIdTest() throws Exception {
        val house = createHouse();
        this.mockMvc.perform(get(HOUSE_BY_ID_URL, house.getId()).accept(MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test house"))
                .andExpect(jsonPath("$.description").value("Test description"))
                .andExpect(jsonPath("$.constructionYear").value(1980))
                .andExpect(jsonPath("$.location").value("Test location"))
                .andExpect(jsonPath("$.numberOfFloor").value(10))
                .andExpect(jsonPath("$.totalSizeInSqm").value(1000.00));
    }

    @Test
    public void notFoundHouseByIdTest() throws Exception {
        val randomId = UUID.randomUUID();
        this.mockMvc.perform(get(HOUSE_BY_ID_URL, randomId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllHousesTest() throws Exception {
        createHouse();
        createHouse();
        this.mockMvc.perform(get(HOUSES_URL)
                        .contentType(MediaTypes.HAL_JSON_VALUE)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.houses").isArray())
                .andExpect(jsonPath("$._embedded.houses", hasSize(2)));
    }

    @Test
    public void findAllHousesWithoutPaginationTest() throws Exception {
        createHouse();
        createHouse();
        this.mockMvc.perform(get(HOUSES_URL)
                        .contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.houses").isArray())
                .andExpect(jsonPath("$._embedded.houses", hasSize(2)));
    }


    @Test
    public void deleteHouseTest() throws Exception {
        House house = createHouse();

        this.mockMvc.perform(delete(HOUSE_BY_ID_URL, house.getId()).contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isNoContent());

        assertThat(houseRepository.findById(house.getId())).isNotPresent();
    }

    private House createHouse() {
        val house = new House();
        house.setTitle("Test house");
        house.setDescription("Test description");
        house.setConstructionYear(1980);
        house.setLocation("Test location");
        house.setNumberOfFloor(10);
        house.setTotalSizeInSqm(1000.00);
        return houseRepository.saveAndFlush(house);
    }
}
