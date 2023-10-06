package com.javaSchool.FinalTask3.repositories;

import com.javaSchool.FinalTask3.entities.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, String> {
}
