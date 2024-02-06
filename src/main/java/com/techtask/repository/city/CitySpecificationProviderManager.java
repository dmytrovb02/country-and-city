package com.techtask.repository.city;

import com.techtask.model.City;
import com.techtask.repository.SpecificationProvider;
import com.techtask.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CitySpecificationProviderManager implements SpecificationProviderManager<City> {
    private final Set<SpecificationProvider<City>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<City> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findAny()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specification for key " + key));
    }
}
