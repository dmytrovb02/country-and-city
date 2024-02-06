package com.techtask.dto.city;

import com.techtask.dto.country.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {

    private Long id;
    private String name;
    private String logoUrl;
    private CountryDto countryDto;
}
