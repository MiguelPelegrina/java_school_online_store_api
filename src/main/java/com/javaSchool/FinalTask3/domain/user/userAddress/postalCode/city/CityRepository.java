package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String> {
}
