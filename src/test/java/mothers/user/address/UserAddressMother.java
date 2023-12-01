package mothers.user.address;

import com.java_school.final_task.domain.user.user_address.UserAddressDTO;
import com.java_school.final_task.domain.user.user_address.UserAddressEntity;
import mothers.user.address.postal_code.PostalCodeMother;

public class UserAddressMother {
    public static UserAddressEntity createUserAddress() {
        return UserAddressEntity.builder()
                .postalCode(PostalCodeMother.createPostalCode())
                .street("Street")
                .number("1")
                .isActive(true)
                .build();
    }

    public static UserAddressDTO createUserAddressDTO() {
        return UserAddressDTO.builder()
                .postalCode(PostalCodeMother.createPostalCodeDTO())
                .street("Street")
                .number("1")
                .isActive(true)
                .build();
    }
}
