package com.javaSchool.FinalTask3.domain.book.parameter.format;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link BookParametersFormatEntity}
 * identified by an ID with String values.
 */
@Repository
public interface BookParametersFormatRepository extends JpaRepository<BookParametersFormatEntity, String> {
}
