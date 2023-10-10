package com.javaSchool.FinalTask3.domain.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// TODO Might need JpaSpecificationExecutor
@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, String> {
}
