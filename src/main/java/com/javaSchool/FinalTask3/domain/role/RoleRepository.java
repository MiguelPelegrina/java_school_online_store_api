package com.javaSchool.FinalTask3.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link RoleEntity} identified by an ID with
 * String values.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}
