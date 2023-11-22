package com.java_school.final_task.domain.book.parameter.format;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link BookParametersFormatEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BookParametersFormatDTO {
    private String name;
}
