package com.java_school.final_task.domain.user.userAddress.postalCode.city.country;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link CountryEntity} identified by an ID
 * with String values.
 */
@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, String>, QuerydslPredicateExecutor<CountryEntity> {
    List<CountryEntity> findAll(Predicate predicate);
}
