package com.java_school.final_task.domain.user.userAddress.postalCode.city.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO I am not sure if I should use @Data or @Value as some say DTO should be immutable and others say I should be
//  mutable ¯\_(ツ)_/¯
/**
 * Data Transfer Object (DTO) of {@link CountryEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CountryDTO {
    private String name;
    private boolean isActive;
}
