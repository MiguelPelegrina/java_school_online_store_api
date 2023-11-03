package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link PostalCodeEntity} identified by
 * an ID with Integer values.
 */
@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCodeEntity, String>, QuerydslPredicateExecutor<PostalCodeEntity> {
    List<PostalCodeEntity> findAll(Predicate predicate);
}
