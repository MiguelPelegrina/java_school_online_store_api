package com.javaSchool.FinalTask3.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link UserEntity} identified by an ID
 * with Integer values.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserByEmail(String email);
}
