package com.java_school.final_task.domain.user.userAddress.postalCode;

import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
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
