package com.javaSchool.FinalTask3.domain.book.parameter;

import com.javaSchool.FinalTask3.domain.book.parameter.format.BookParametersFormatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link BookParameterEntity}
 * identified by an ID with Integer values.
 */
@Repository
public interface BookParameterRepository extends JpaRepository<BookParameterEntity, Integer> {
    List<BookParameterEntity> findByAuthorAndFormat(String author, BookParametersFormatEntity format);
}
