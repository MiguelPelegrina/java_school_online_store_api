package com.javaSchool.FinalTask3.domain.book;

import com.javaSchool.FinalTask3.utils.AbstractPageableSortableRequest;
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
    private Optional<Boolean> active;
    private String genre = "";
}