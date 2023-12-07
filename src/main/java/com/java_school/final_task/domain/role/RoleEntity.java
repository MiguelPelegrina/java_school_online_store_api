package com.java_school.final_task.domain.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Schema(description = "Role that can be assigned to a user")
@Table(name = "roles", schema = "public", catalog = "online_store")
public class RoleEntity {
    @Id
    @Column(name = "name", length = 45)
    private String name;
}
