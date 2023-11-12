package com.javaSchool.FinalTask3.domain.book.parameter;

import com.javaSchool.FinalTask3.domain.book.parameter.format.BookParametersFormatDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link BookParameterEntity}.
 */
@Data
@NoArgsConstructor
public class BookParameterDTO {
    private int id;
    private String author;
    private BookParametersFormatDTO format;
    private boolean isActive;

    public BookParameterDTO(BookParameterEntity bookParameter){
        this.id = bookParameter.getId();
        this.author = bookParameter.getAuthor();
        this.format = new BookParametersFormatDTO(bookParameter.getFormat().getName());
        this.isActive = bookParameter.isActive();
    }
}
