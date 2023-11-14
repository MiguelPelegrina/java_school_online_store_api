package com.javaSchool.FinalTask3.domain.user;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link UserEntity} identified by an ID
 * with Integer values.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, QuerydslPredicateExecutor<UserEntity> {
    // TODO Test Custom Query Methods?
    Optional<UserEntity> findUserByEmail(String email);
    List<UserEntity> findAll(Predicate predicate);
}
