package com.javaSchool.FinalTask3.dtos;

import com.javaSchool.FinalTask3.entities.Country;
import lombok.Data;

@Data
public class CityDTO {
    private String name;

    private boolean isActive;

    private String countryName;
}
