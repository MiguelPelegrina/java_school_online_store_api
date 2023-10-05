package com.javaSchool.FinalTask3.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "cities", schema = "public", catalog = "online_store")
public class City {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    // TODO With DTO all fetch types are lazy?
    // TODO Cascade type?
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_name", referencedColumnName = "name", nullable = false)
    private Country country;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
