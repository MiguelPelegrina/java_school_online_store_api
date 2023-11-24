package mothers.user;

import com.java_school.final_task.domain.user.UserDTO;
import com.java_school.final_task.domain.user.UserEntity;
import mothers.user.address.UserAddressMother;
import mothers.user_role.UserRoleMother;

import java.time.LocalDate;
import java.util.Set;

public class UserMother {
    public static UserEntity createUser(){
        return UserEntity.builder()
                .id(4)
                .active(true)
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .password("Password")
                .name("Name")
                .surname("Surname")
                .roles(Set.of(UserRoleMother.createUserRoleAdmin()))
                .address(UserAddressMother.createUserAddress())
                .build();
    }

    public static UserDTO createUserDTO(){
        return UserDTO.builder()
                .id(4)
                .isActive(true)
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .name("Name")
                .surname("Surname")
                .roles(Set.of())
                .address(UserAddressMother.createUserAddressDTO())
                .build();
    }
}
