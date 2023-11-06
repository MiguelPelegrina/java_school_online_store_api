package com.javaSchool.FinalTask3.domain.order.deliveryMethod;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link DeliveryMethodEntity} identified
 * by an ID with String values.
 */
@Repository
public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethodEntity, String>, QuerydslPredicateExecutor<DeliveryMethodEntity> {
    List<DeliveryMethodEntity> findAll(Predicate predicate);
}
