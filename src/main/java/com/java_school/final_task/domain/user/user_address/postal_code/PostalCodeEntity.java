package com.java_school.final_task.domain.user.user_address.postal_code;

import com.java_school.final_task.domain.user.user_address.postal_code.city.CityEntity;
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
@Schema(description = "Postal code of a city")
@Table(name = "postal_codes", schema = "public", catalog = "online_store")
public class PostalCodeEntity {
    @Id
    @Column(name = "code", length = 45)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_name", referencedColumnName = "name", nullable = false)
    private CityEntity city;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
