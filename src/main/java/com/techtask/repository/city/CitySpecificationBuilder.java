package com.techtask.repository.city;

import com.techtask.dto.city.CitySearchParametersDto;
import com.techtask.model.City;
import com.techtask.repository.SpecificationBuilder;
import com.techtask.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitySpecificationBuilder implements SpecificationBuilder<City> {
    private final SpecificationProviderManager<City> specificationProviderManager;

    @Override
    public Specification<City> build(CitySearchParametersDto citySearchParametersDto) {
        Specification<City> spec = Specification.where(null);

        if (citySearchParametersDto.names() != null
                && citySearchParametersDto.names().length > 0) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider("name")
                    .getSpecification(citySearchParametersDto.names()));
        }

        if (citySearchParametersDto.countryNames() != null
                && citySearchParametersDto.countryNames().length > 0) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider("countryName")
                    .getSpecification(citySearchParametersDto.countryNames()));
        }

        return spec;
    }
}
