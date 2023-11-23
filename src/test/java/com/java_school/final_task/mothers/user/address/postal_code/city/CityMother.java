package com.java_school.final_task.mothers.user.address.postal_code.city;

import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityEntity;
import com.java_school.final_task.mothers.user.address.postal_code.city.country.CountryMother;

// TODO Randomize values
public class CityMother {
    public static CityEntity createCity(){
        return CityEntity.builder()
                .name("City")
                .countryName(CountryMother.createCountry())
                .isActive(true)
                .build();
    }

    public static CityDTO createCityDTO(){
        return CityDTO.builder()
                .name("City")
                .country(CountryMother.createCountryDTO())
                .isActive(true)
                .build();
    }
}
