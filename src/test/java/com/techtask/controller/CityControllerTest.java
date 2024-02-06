package com.techtask.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void getAllCities() throws Exception {
        mockMvc.perform(get("/api/cities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser
    @Sql(scripts = "classpath:database/add-for-city-tests.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/delete-for-city-tests.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetUniqueCityNames() throws Exception {
        List<String> expectedCityNames = Arrays.asList("City1", "City2", "City3");

        MvcResult result = mockMvc.perform(get("/api/cities/name"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> actualCityNames = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertEquals(actualCityNames, expectedCityNames);
    }

    @Test
    @WithMockUser(username = "editor", authorities = {"EDITOR"})
    @Sql(scripts = "classpath:database/add-for-city-tests.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/delete-for-city-tests.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateCity() throws Exception {
        Long cityId = 1L;
        String cityName = "New City Name";
        String cityLogoUrl = "http://example.com/logo.jpg";

        String requestBody = "{"
                + "\"name\": \"" + cityName + "\","
                + "\"logoUrl\": \"" + cityLogoUrl + "\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cities/{id}", cityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(cityName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.logoUrl").value(cityLogoUrl));
    }
}
