package com.javaSchool.FinalTask3.domain.user.userAddress;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeDTO;
import lombok.Data;

/**
 * Data Transfer Object (DTO) of {@link UserAddressEntity}.
 */
@Data
public class UserAddressDTO {
    private int id;
    // TODO Not sure if right
    private PostalCodeDTO postalCode;
    private String street;
    private int number;
    private boolean isActive;
}
