package com.javaSchool.FinalTask3.dtos;

import lombok.Data;

@Data
public class BookParameterDTO {
    private int id;
    private String author;
    private String format;
    private boolean isActive;
}
