package com.java_school.final_task.domain.user.user_address.postal_code.city;

import com.java_school.final_task.domain.user.user_address.postal_code.city.country.CountryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Schema(description = "City of a country")
@Table(name = "cities", schema = "public", catalog = "online_store")
public class CityEntity {
    @Id
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_name", referencedColumnName = "name", nullable = false)
    private CountryEntity countryName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public CityEntity(String name) {
        this.name = name;
    }
}
