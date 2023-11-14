package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country;

import lombok.Builder;
import lombok.Data;

// TODO I am not sure if I should use @Data or @Value as some say DTO should be immutable and others say I should be
//  mutable ¯\_(ツ)_/¯
/**
 * Data Transfer Object (DTO) of {@link CountryEntity}.
 */
@Builder
@Data
public class CountryDTO {
    private String name;
    private boolean isActive;
}
