package com.javaSchool.FinalTask3.domain.order.paymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link PaymentStatusEntity} identified
 * by an ID with String values.
 */
@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatusEntity, String> {
}
