package com.java_school.final_task.domain.book.dto;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Data Transfer Object of a book with the total amount of times it was sold")
public class NumberedBookDTO extends BookDTO {
    private int totalAmount;

    public NumberedBookDTO(BookEntity book, int totalAmount) {
        super();
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setPrice(book.getPrice());
        this.setGenre(new BookGenreDTO(book.getGenre().getName()));
        this.setIsbn(book.getIsbn());
        this.setParameters(new BookParameterDTO(book.getParameters()));
        this.setStock(book.getStock());
        this.setActive(book.isActive());
        this.setImage(book.getImage());
        this.totalAmount = totalAmount;
    }
}
