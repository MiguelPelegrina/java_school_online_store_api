package com.javaSchool.FinalTask3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Countries")
public class Country {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;
}
