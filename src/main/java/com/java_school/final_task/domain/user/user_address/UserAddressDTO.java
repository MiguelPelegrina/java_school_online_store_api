package com.java_school.final_task.domain.user.user_address;

import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link UserAddressEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a user address")
public class UserAddressDTO {
    private int id;
    private PostalCodeDTO postalCode;
    private String street;
    private String number;
    private boolean isActive;
}
