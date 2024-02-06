package com.techtask.repository.city.specification;

import com.techtask.model.City;
import com.techtask.model.Country;
import com.techtask.repository.SpecificationProvider;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CountryNameSpecificationProvider implements SpecificationProvider<City> {

    @Override
    public String getKey() {
        return "countryName";
    }

    @Override
    public Specification<City> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Join<City, Country> countryJoin = root.join("country");

            return countryJoin.get("name").in(params);
        };
    }
}
