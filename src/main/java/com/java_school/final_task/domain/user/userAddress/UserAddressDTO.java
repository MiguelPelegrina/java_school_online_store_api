package com.java_school.final_task.domain.user.userAddress;

import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeDTO;
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
public class UserAddressDTO {
    private int id;
    private PostalCodeDTO postalCode;
    private String street;
    private String number;
    private boolean isActive;
}
