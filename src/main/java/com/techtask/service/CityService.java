package com.techtask.service;

import com.techtask.dto.city.CityDto;
import com.techtask.dto.city.CityRequestDto;
import com.techtask.dto.city.CitySearchParametersDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CityService {
    List<CityDto> getAllCities(Pageable pageable);
    List<String> getUniqueCityNames();
    CityDto updateCityNameAndLogo(Long id, CityRequestDto cityRequestDto);
    List<CityDto> getCitiesByNameOrCountry(CitySearchParametersDto params);
}
