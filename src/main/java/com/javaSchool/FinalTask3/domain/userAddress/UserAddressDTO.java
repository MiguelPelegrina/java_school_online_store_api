package com.javaSchool.FinalTask3.domain.userAddress;

import com.javaSchool.FinalTask3.domain.postalCode.PostalCodeDTO;
import com.javaSchool.FinalTask3.domain.user.UserDTO;
import lombok.Data;

@Data
public class UserAddressDTO {
    private int id;
    // TODO Not sure if right
    private PostalCodeDTO postalCode;
    private UserDTO user;
    private String street;
    private int number;
    private boolean isActive;
}
