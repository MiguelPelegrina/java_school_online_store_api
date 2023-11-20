package com.java_school.final_task.domain.userRole;

import com.java_school.final_task.domain.user.UserEntity;
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
    Optional<List<UserRoleEntity>> findByUser(UserEntity user);
}
