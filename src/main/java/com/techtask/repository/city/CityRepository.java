package com.techtask.repository.city;

import com.techtask.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    @Query("SELECT DISTINCT c.name FROM City c")
    List<String> findDistinctCityNames();
}
