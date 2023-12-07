package com.java_school.final_task.domain.book.dto;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) of {@link BookEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a book")
public class BookDTO {
    private int id;
    private Boolean active;
    private String title;
    private BigDecimal price;
    private BookGenreDTO genre;
    private String isbn;
    private BookParameterDTO parameters;
    private int stock;
    private boolean isActive;
    private String image;
}
