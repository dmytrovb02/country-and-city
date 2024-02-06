package com.techtask.service.impl;

import com.techtask.dto.city.CityDto;
import com.techtask.dto.city.CityRequestDto;
import com.techtask.dto.city.CitySearchParametersDto;
import com.techtask.exception.EntityNotFoundException;
import com.techtask.mapper.CityMapper;
import com.techtask.model.City;
import com.techtask.repository.city.CityRepository;
import com.techtask.repository.city.CitySpecificationBuilder;
import com.techtask.service.CityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final CitySpecificationBuilder citySpecificationBuilder;

    @Override
    public List<CityDto> getCitiesByNameOrCountry(CitySearchParametersDto params) {
        Specification<City> citySpecification = citySpecificationBuilder.build(params);
        return cityRepository.findAll(citySpecification).stream()
                .map(cityMapper::mapToDto)
                .toList();
    }

    @Override
    public List<CityDto> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).stream()
                .map(cityMapper::mapToDto)
                .toList();
    }

    @Override
    public List<String> getUniqueCityNames() {
        return cityRepository.findDistinctCityNames();
    }

    @Override
    public CityDto updateCityNameAndLogo(Long id, CityRequestDto cityRequestDto) {
        City city = getCityById(id);
        cityMapper.updateCityFromDto(cityRequestDto, city);
        cityRepository.save(city);
        return cityMapper.mapToDto(city);
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("City with id: %s not found", id)));
    }
}
