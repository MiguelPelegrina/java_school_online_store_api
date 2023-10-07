package com.javaSchool.FinalTask3.entities.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@RequiredArgsConstructor
public class UserRoleId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "role_id", nullable = false, length = 45)
    private String roleId;
}
