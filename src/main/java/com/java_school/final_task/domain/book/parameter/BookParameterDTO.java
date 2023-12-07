package com.java_school.final_task.domain.book.parameter;

import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link BookParameterEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a book parameter")
public class BookParameterDTO {
    private int id;
    private String author;
    private BookParametersFormatDTO format;
    private boolean isActive;

    public BookParameterDTO(BookParameterEntity bookParameter) {
        this.id = bookParameter.getId();
        this.author = bookParameter.getAuthor();
        this.format = new BookParametersFormatDTO(bookParameter.getFormat().getName());
        this.isActive = bookParameter.isActive();
    }
}
