package com.techtask.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.techtask.repository.city.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CityRepositoryTests {

    @Autowired
    private CityRepository cityRepository;

    @Test
    @Sql(scripts = "classpath:database/add-for-city-tests.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/delete-for-city-tests.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findDistinctCityNames_ReturnsDistinctNames() {
        List<String> distinctCityNames = cityRepository.findDistinctCityNames();

        assertEquals(3, distinctCityNames.size());
        assertTrue(distinctCityNames.contains("City1"));
        assertTrue(distinctCityNames.contains("City2"));
        assertTrue(distinctCityNames.contains("City3"));
    }
}
