package com.javaSchool.FinalTask3.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link BookEntity} identified by an ID with
 * Integer values.
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
