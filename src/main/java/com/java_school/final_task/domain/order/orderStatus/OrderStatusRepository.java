package com.java_school.final_task.domain.order.orderStatus;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link OrderStatusEntity} identified by
 * an ID with String values.
 */
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, String>, QuerydslPredicateExecutor<OrderStatusEntity> {
    List<OrderStatusEntity> findAll(Predicate predicate);
}
