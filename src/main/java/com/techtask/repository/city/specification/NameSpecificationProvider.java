package com.techtask.repository.city.specification;

import com.techtask.model.City;
import com.techtask.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NameSpecificationProvider implements SpecificationProvider<City> {

    @Override
    public String getKey() {
        return "name";
    }

    @Override
    public Specification<City> getSpecification(String[] params) {
        return (root, query, criteriaBuilder)
                -> root.get("name").in(Arrays.stream(params).toArray());
    }

}
