package mothers.user.address.postal_code.city.country;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryDTO;
import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryEntity;

public class CountryMother {
    public static CountryEntity createCountry() {
        return CountryEntity.builder()
                .isActive(true)
                .name("Country")
                .build();
    }

    public static CountryDTO createCountryDTO() {
        return CountryDTO.builder()
                .isActive(true)
                .name("Country")
                .build();
    }
}
