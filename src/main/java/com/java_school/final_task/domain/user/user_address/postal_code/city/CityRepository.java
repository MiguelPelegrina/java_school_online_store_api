package com.java_school.final_task.domain.user.user_address.postal_code.city;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link CityEntity} identified by an ID with
 * String values.
 */
@Repository
public interface CityRepository extends JpaRepository<CityEntity, String>, QuerydslPredicateExecutor<CityEntity> {
    List<CityEntity> findAll(Predicate predicate);
}
