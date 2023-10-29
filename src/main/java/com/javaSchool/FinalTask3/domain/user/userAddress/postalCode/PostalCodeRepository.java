package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCodeEntity, String> {
}
