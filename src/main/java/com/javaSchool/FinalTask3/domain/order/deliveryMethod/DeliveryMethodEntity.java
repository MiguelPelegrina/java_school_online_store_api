package com.javaSchool.FinalTask3.domain.order.deliveryMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "delivery_methods", schema = "public", catalog = "online_store")
public class DeliveryMethodEntity {
    @Id
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
