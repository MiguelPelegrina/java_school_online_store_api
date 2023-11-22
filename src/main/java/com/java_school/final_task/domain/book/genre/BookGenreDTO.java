package com.java_school.final_task.domain.book.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link BookGenreEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BookGenreDTO {
    private String name;
}
