package com.java_school.final_task.domain.user.user_address.postal_code;

import com.java_school.final_task.domain.user.user_address.postal_code.city.CityDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link PostalCodeEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a postal code")
public class PostalCodeDTO {
    private String code;
    private boolean isActive;
    private CityDTO city;
}
