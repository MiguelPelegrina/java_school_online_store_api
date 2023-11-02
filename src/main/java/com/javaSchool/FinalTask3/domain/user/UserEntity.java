package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.domain.user.userAddress.UserAddressEntity;
import com.javaSchool.FinalTask3.domain.userRole.UserRoleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    private String phoneNumber;

    // TODO Change OneToMAny
    @OneToOne(fetch = FetchType.LAZY)
    private UserAddressEntity address;

    // TODO Not sure if right
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRoleEntity> roles;
}
