package com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.city.country;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, String>, QuerydslPredicateExecutor<CountryEntity> {
    List<CountryEntity> findAll(Predicate predicate);
}
