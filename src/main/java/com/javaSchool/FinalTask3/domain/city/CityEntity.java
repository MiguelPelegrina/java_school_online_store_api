package com.javaSchool.FinalTask3.domain.city;

import com.javaSchool.FinalTask3.domain.country.CountryEntity;
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
public class CityEntity {
    @Id
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    // TODO With DTOs all fetch types are lazy?
    // TODO Cascade type? --> At the moment it is not part of the requirements, the database admin can insert new
    //  countries when the customer asks for it, but what happens when a country is deleted?
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_name", referencedColumnName = "name", nullable = false)
    private CountryEntity countryName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    // TODO Not sure if necessary
    public CityEntity(String name){
        this.name = name;
    }
}
