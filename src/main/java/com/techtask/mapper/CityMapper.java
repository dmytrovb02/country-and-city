package com.techtask.mapper;

import com.techtask.dto.city.CityDto;
import com.techtask.dto.city.CityRequestDto;
import com.techtask.model.City;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(source = "country", target = "countryDto")
    CityDto mapToDto(City city);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCityFromDto(CityRequestDto dto, @MappingTarget City entity);
}
