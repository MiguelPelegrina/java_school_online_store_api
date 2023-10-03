package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
