package com.java_school.final_task.domain.user.impl;

import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.user.UserRepository;
import com.java_school.final_task.domain.user.UserRestController;
import com.java_school.final_task.domain.user.dto.UserDTO;
import com.java_school.final_task.domain.user.dto.UserRequestDTO;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of the {@link UserEntity} entity. Handles the REST methods. Uses
 * {@link UserDTO} as returning object.
 */
@RequestMapping(path = "users")
@RestController
public class UserRestControllerImpl
        extends AbstractRestControllerImpl<UserServiceImpl, UserRepository, UserEntity, UserDTO, Integer>
        implements UserRestController {
    /**
     * All arguments constructor.
     *
     * @param service {@link UserServiceImpl} of the {@link UserEntity} entity.
     */
    public UserRestControllerImpl(UserServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved a list of users",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of users")
    @Override
    public ResponseEntity<Page<UserDTO>> getAllInstances(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User request", required = true,
                    content = @Content(schema = @Schema(implementation = UserRequestDTO.class)))
            UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(this.service.getAllInstances(userRequestDTO));
    }
}
