package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// TODO Might need JpaSpecificationExecutor
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
