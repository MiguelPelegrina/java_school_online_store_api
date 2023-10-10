package com.javaSchool.FinalTask3.domain.postalCode;

import lombok.Data;

@Data
public class PostalCodeDTO {
    private String code;
    private boolean isActive;
    private String cityName;
}
