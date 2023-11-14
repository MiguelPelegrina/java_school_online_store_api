package com.javaSchool.FinalTask3.domain.book;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link BookEntity} identified by an ID with
 * Integer values.
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer>, QuerydslPredicateExecutor<BookEntity> {
    // TODO Test Custom Query Methods?
    List<BookEntity> findAll(Predicate predicate);
}
