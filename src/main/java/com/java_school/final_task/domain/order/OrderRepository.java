package com.java_school.final_task.domain.order;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link OrderEntity} identified by an ID with
 * Integer values.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>, QuerydslPredicateExecutor<OrderEntity> {
    List<OrderEntity> findAll(Predicate predicate);
}
