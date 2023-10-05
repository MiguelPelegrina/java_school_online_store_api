package com.javaSchool.FinalTask3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// TODO Why does Postman show "is_active" as "active". When I want to save a new Country the field name must be active (
//  not is_active)
@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "countries", schema = "public", catalog = "online_store")
public class Country {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
