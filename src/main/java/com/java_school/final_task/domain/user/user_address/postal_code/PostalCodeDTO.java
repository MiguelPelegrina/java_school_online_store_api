package com.java_school.final_task.domain.user.user_address.postal_code;

import com.java_school.final_task.domain.user.user_address.postal_code.city.CityDTO;
import lombok.*;

/**
 * Data Transfer Object (DTO) of {@link PostalCodeEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PostalCodeDTO {
    private String code;
    private boolean isActive;
    private CityDTO city;
}
