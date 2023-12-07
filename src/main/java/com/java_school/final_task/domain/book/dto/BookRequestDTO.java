package com.java_school.final_task.domain.book.dto;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.utils.AbstractPageableSortableRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Request class for {@link BookEntity}. Contains the specified parameters and sorting criteria to search for in the
 * {@link BookRepository}.
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "A request to retrieve a filtered list of books")
public class BookRequestDTO extends AbstractPageableSortableRequest {
    private String name = "";
    private Optional<Boolean> active = Optional.empty();
    private String genre = "";
}