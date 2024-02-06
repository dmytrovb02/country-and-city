package com.techtask.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.techtask.dto.city.CityDto;
import com.techtask.mapper.CityMapper;
import com.techtask.model.City;
import com.techtask.repository.city.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {
    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    public void getAllCities_ReturnsListOfCityDtos() {
        Pageable pageable = mock(Pageable.class);

        List<City> cities = Arrays.asList(new City(), new City(), new City());
        List<CityDto> cityDtos = Arrays.asList(new CityDto(), new CityDto(), new CityDto());
        Page<City> cityPage = new PageImpl<>(cities);

        when(cityRepository.findAll(pageable)).thenReturn(cityPage);
        when(cityMapper.mapToDto(any(City.class))).thenReturn(new CityDto());

        List<CityDto> result = cityService.getAllCities(pageable);

        assertEquals(cityDtos.size(), result.size());
    }

    @Test
    void getUniqueCityNames_ReturnsUniqueNames() {
        List<String> expectedUniqueNames = Arrays.asList("City1", "City2", "City3");
        when(cityRepository.findDistinctCityNames()).thenReturn(expectedUniqueNames);

        List<String> actualUniqueNames = cityService.getUniqueCityNames();

        assertEquals(expectedUniqueNames, actualUniqueNames);
        verify(cityRepository, times(1)).findDistinctCityNames();
    }

    @Test
    void getUniqueCityNames_EmptyListWhenRepositoryReturnsEmpty() {
        when(cityRepository.findDistinctCityNames()).thenReturn(List.of());

        List<String> actualUniqueNames = cityService.getUniqueCityNames();

        assertEquals(0, actualUniqueNames.size());
    }
}
