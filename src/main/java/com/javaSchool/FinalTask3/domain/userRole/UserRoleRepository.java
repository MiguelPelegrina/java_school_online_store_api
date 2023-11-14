package com.javaSchool.FinalTask3.domain.userRole;

import com.javaSchool.FinalTask3.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface that extends from JpaRepository. Accesses the table of the entity {@link UserRoleRepository} identified by
 * an ID with Integer values.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    // TODO Test Custom Query Methods?
    Optional<List<UserRoleEntity>> findByUser(UserEntity user);
}
