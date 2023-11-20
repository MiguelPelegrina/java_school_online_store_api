package com.java_school.final_task.domain.book;

import com.java_school.final_task.utils.AbstractPageableSortableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Request class for {@link BookEntity}. Contains the specified parameters and sorting criteria to search for in the
 * {@link BookRepository}.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookRequest extends AbstractPageableSortableRequest {
    private String name = "";
    private Optional<Boolean> active = Optional.empty();
    private String genre = "";
}