package mothers.user.address.postal_code.city;

import com.java_school.final_task.domain.user.user_address.postal_code.city.CityDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.city.CityEntity;
import mothers.user.address.postal_code.city.country.CountryMother;

public class CityMother {
    public static CityEntity createCity() {
        return CityEntity.builder()
                .name("City")
                .countryName(CountryMother.createCountry())
                .isActive(true)
                .build();
    }

    public static CityDTO createCityDTO() {
        return CityDTO.builder()
                .name("City")
                .country(CountryMother.createCountryDTO())
                .isActive(true)
                .build();
    }
}
