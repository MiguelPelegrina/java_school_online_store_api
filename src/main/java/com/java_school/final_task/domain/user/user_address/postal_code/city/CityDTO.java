package com.java_school.final_task.domain.user.user_address.postal_code.city;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object of a city")
public class CityDTO {
    private String name;
    private boolean isActive;
    private CountryDTO country;
}
