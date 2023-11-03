package com.javaSchool.FinalTask3.domain.order.orderStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link OrderStatusEntity} identified by
 * an ID with String values.
 */
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, String> {
}
