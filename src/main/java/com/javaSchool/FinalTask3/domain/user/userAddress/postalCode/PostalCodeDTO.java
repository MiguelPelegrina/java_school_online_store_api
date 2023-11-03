package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode;

import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link PostalCodeEntity}.
 */
@Data
public class PostalCodeDTO {
    private String code;
    private boolean isActive;
    private String cityName;
}
