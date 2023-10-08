package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link Book} identified by an ID with
 * Integer values.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
