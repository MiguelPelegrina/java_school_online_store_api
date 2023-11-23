package com.java_school.final_task.mothers.user.address;

import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.user.userAddress.UserAddressEntity;
import com.java_school.final_task.mothers.user.address.postal_code.PostalCodeMother;

// TODO Randomize values
public class UserAddressMother {
    public static UserAddressEntity createUserAddress(){
        return UserAddressEntity.builder()
                .postalCode(PostalCodeMother.createPostalCode())
                .street("Street")
                .number("1")
                .isActive(true)
                .build();
    }

    public static UserAddressDTO createUserAddressDTO(){
        return UserAddressDTO.builder()
                .postalCode(PostalCodeMother.createPostalCodeDTO())
                .street("Street")
                .number("1")
                .isActive(true)
                .build();
    }
}
