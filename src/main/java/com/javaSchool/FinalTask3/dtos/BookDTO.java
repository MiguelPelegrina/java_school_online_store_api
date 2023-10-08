package com.javaSchool.FinalTask3.dtos;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) of {@link com.javaSchool.FinalTask3.entities.Book}.
 */
@Data
public class BookDTO {
    private int id;
    private String title;
    private BigDecimal price;
    private String genre;
    // TODO Not sure if right
    private BookParameterDTO parameters;
    private int stock;
    private boolean isActive;
    private String image;
}
