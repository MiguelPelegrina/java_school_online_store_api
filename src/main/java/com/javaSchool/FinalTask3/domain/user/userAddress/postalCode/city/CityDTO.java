package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link CityEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CityDTO {
    private String name;
    private boolean isActive;
    private CountryDTO country;
}
