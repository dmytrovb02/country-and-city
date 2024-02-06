package com.techtask.controller;

import com.techtask.dto.city.CityDto;
import com.techtask.dto.city.CityRequestDto;
import com.techtask.dto.city.CitySearchParametersDto;
import com.techtask.service.impl.CityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityServiceImpl cityService;

    @GetMapping
    @Operation(summary = "Get all cities", description = "Get a list of all available cities")
    public List<CityDto> getAllCities(Pageable pageable) {
        return cityService.getAllCities(pageable);
    }

    @GetMapping("/name")
    @Operation(summary = "Get all unique city names", description = "Get a list of all available unique city names")
    public List<String> getUniqueCityNames() {
        return cityService.getUniqueCityNames();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('EDITOR')")
    @Operation(summary = "Update a city by id", description = "Update a city name and logo by id. Only allowed for users with role EDITOR")
    public CityDto updateCity(@PathVariable Long id,
                           @RequestBody @Valid CityRequestDto cityRequestDto) {
        return cityService.updateCityNameAndLogo(id, cityRequestDto);
    }

    @GetMapping("/search")
    @Operation(summary = "Search cities", description = "Search cities by name and name of country")
    public List<CityDto> searchCities(CitySearchParametersDto searchParameters) {
        return cityService.getCitiesByNameOrCountry(searchParameters);
    }
}
