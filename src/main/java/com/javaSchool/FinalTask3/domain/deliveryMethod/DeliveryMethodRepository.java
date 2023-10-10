package com.javaSchool.FinalTask3.domain.deliveryMethod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethodEntity, String> {
}
