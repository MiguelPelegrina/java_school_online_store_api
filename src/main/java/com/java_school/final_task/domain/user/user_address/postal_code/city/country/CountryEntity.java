package com.java_school.final_task.domain.user.user_address.postal_code.city.country;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Schema(description = "Country")
@Table(name = "countries", schema = "public", catalog = "online_store")
public class CountryEntity implements Serializable {
    @Id
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public CountryEntity(String name) {
        this.name = name;
    }
}
