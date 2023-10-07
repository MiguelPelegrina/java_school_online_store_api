package com.javaSchool.FinalTask3.dtos;

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
