package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressEntity;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "users", schema = "public", catalog = "online_store")
public class UserEntity {
    // Class methods
    /**
     * Checks if the user's role allows them to update another user.
     * @param otherUser  User that will be compared.
     * @return true if the activeUser is allowed to update the userToBeUpdated, otherwise false.
     */
    public boolean hasMoreRightThen(UserEntity otherUser){
        return this.isAdmin() && (otherUser.isClient() || otherUser.isEmployee()) ||
                this.isEmployee() && otherUser.isClient();
    }

    // Fields
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "email_address", nullable = false, length = 45, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "phone_number", nullable = false, length = 45)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserAddressEntity address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRoleEntity> roles;

    // Instance methods
    // Auxiliary methods
    /**
     * Checks if the current user is a "ADMIN" by iterating through the user's roles.
     * @return true if the user has the "ADMIN" role, otherwise false.
     */
    public boolean isAdmin(){
        boolean isAdmin = false;

        for(UserRoleEntity userRole : roles){
            if (userRole.getRole().getName().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        return isAdmin;
    }

    /**
     * Checks if the current user is a "CLIENT" by iterating through the user's roles.
     * @return true if the user has the "CLIENT" role, otherwise false.
     */
    public boolean isClient(){
        boolean isClient = false;

        for(UserRoleEntity userRole : roles){
            if (userRole.getRole().getName().equals("CLIENT")) {
                isClient = true;
                break;
            }
        }

        return isClient;
    }

    /**
     * Checks if the current user is a "EMPLOYEE" by iterating through the user's roles.
     * @return true if the user has the "EMPLOYEE" role, otherwise false.
     */
    public boolean isEmployee(){
        boolean isEmployee = false;

        for(UserRoleEntity userRole : roles){
            if (userRole.getRole().getName().equals("EMPLOYEE")) {
                isEmployee = true;
                break;
            }
        }

        return isEmployee;
    }
}
