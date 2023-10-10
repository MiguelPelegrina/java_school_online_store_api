package com.javaSchool.FinalTask3.domain.country;

import lombok.Data;

// TODO I am not sure if I should use @Data or @Value as some say DTO should be immutable and others say I should be
//  mutable ¯\_(ツ)_/¯
@Data
public class CountryDTO {
    private String name;
    // TODO Might be an unnecessary attribute?
    private boolean isActive;
}
