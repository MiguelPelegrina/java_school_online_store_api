package com.javaSchool.FinalTask3.domain.user;

import com.javaSchool.FinalTask3.utils.AbstractPageableSortableRequest;
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
public class UserRequest extends AbstractPageableSortableRequest {
    private String name = "";
    private Optional<Boolean> active = Optional.empty();
    // TODO Scale by roles
    // private String role = "";
}
