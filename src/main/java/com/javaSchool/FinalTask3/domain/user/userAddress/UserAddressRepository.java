package com.javaSchool.FinalTask3.domain.user.userAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link UserAddressEntity} identified by
 * an ID with Integer values.
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Integer> {
}
