package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.CityDTO;
import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link PostalCodeEntity}.
 */
@Data
public class PostalCodeDTO {
    private String code;
    private boolean isActive;
    private CityDTO city;
}
