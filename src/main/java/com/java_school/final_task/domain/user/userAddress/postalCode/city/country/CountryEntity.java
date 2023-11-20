package com.java_school.final_task.domain.user.userAddress.postalCode.city.country;

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
@Table(name = "countries", schema = "public", catalog = "online_store")
public class CountryEntity {
    @Id
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public CountryEntity(String name) {
        this.name = name;
    }
}
