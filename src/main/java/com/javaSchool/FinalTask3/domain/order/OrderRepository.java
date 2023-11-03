package com.javaSchool.FinalTask3.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link OrderEntity} identified by an ID with
 * Integer values.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
