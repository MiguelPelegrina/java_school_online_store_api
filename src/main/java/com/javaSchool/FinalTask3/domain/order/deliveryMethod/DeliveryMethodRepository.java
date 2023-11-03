package com.javaSchool.FinalTask3.domain.order.deliveryMethod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link DeliveryMethodEntity} identified
 * by an ID with String values.
 */
@Repository
public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethodEntity, String> {
}
