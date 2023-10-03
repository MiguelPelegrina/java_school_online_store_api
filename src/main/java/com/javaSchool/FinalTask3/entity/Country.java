package com.javaSchool.FinalTask3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Countries")
public class Country {
    @Id
    private String name;

    private boolean isActive;
}
