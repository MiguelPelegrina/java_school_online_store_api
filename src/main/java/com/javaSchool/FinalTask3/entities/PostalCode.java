package com.javaSchool.FinalTask3.entities;

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
@Table(name = "postal_codes", schema = "public", catalog = "online_store")
public class PostalCode {
    @Id
    @Column(name = "code", nullable = false, length = 45)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_name", referencedColumnName = "name", nullable = false)
    private City city;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
