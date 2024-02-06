package com.techtask.repository;

import com.techtask.dto.city.CitySearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {

    Specification<T> build(CitySearchParametersDto bookSearchParametersDto);
}
