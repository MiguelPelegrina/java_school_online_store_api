package com.java_school.final_task.domain.user.user_address.postal_code.city.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
