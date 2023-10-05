package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
}
