package com.javaSchool.FinalTask3.domain.role;

import com.javaSchool.FinalTask3.domain.userRole.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "roles", schema = "public", catalog = "online_store")
public class RoleEntity {
    @Id
    @Column(name = "name", length = 45)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;
}
