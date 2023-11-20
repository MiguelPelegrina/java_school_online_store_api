package com.java_school.final_task.domain.user.userAddress;

import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeDTO;
import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link UserAddressEntity}.
 */
@Data
public class UserAddressDTO {
    private int id;
    private PostalCodeDTO postalCode;
    private String street;
    private int number;
    private boolean isActive;
}
