package com.java_school.final_task.domain.user.dto;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.utils.AbstractPageableSortableRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Request class for {@link UserEntity}. Contains the specified parameters and sorting criteria to search for in the
 * {@link UserRepository}.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "A request to retrieve a filtered list of users")
public class UserRequestDTO extends AbstractPageableSortableRequest {
    private String name = "";
    private Optional<Boolean> active = Optional.empty();
}
