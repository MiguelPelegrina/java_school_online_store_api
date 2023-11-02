package com.javaSchool.FinalTask3.domain.userRole;

import com.javaSchool.FinalTask3.domain.role.RoleEntity;
import com.javaSchool.FinalTask3.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "user_roles", schema = "public", catalog = "online_store")
public class UserRoleEntity {
    // TODO should have embedded id
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;
}
