package com.javaSchool.FinalTask3.domain.book.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link BookGenreEntity} identified by an
 * ID with String values.
 */
@Repository
public interface BookGenreRepository extends JpaRepository<BookGenreEntity, String> {
}
