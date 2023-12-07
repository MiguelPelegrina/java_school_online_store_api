package com.java_school.final_task.utils.impl;

import com.java_school.final_task.utils.AbstractRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The {@code AbstractRestController} class is a parent controller responsible for the interaction between service and
 * user. Obtains data from the services and returns a {@link ResponseEntity} that contains the Data Transfer Object
 * (DTO) of the entity and the status of the operation. Used for entities that only have one attribute and therefore
 * can't be updated.
 *
 * @param <S> The service class that manages the entity.
 * @param <R> The repository class associated with the entity.
 * @param <E> The entity being managed.
 * @param <T> The Data Transfer Object (DTO) of the entity.
 * @param <K> The identifier type of the entity.
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@RestController
public abstract class AbstractRestControllerImpl
        <S extends AbstractServiceImpl<R, E, T, K>,
                R extends JpaRepository<E, K>,
                E, T, K>
        implements AbstractRestController<E, T, K> {
    // Fields
    protected final S service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletes the instance", content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes an instance of an entity")
    @Override
    //@Tag(name = "Delete")
    public ResponseEntity<Object> deleteInstance(@Parameter(description = "id of the instance to be deleted", example = "123")
                                                 @PathVariable K id) {
        service.deleteInstance(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all instances", content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping
    @Operation(summary = "Get all instances of an entity", description = "Retrieves a list of all instances.")
    @Override
    //@Tag(name = "Get all instances")
    public ResponseEntity<List<T>> getAllInstances() {
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the instance",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Instance not found", content = @Content)
    })
    @GetMapping("/{id}")
    @Operation(summary = "Get an instances of an entity by its id")
    @Override
    //@Tag(name = "Get a instance")
    public ResponseEntity<T> getInstanceById(@Parameter(description = "id of the instance to be searched", example = "123")
                                             @PathVariable K id) {
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved or updated the instance",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PostMapping
    @Operation(summary = "Saves an instance of an entity into the repository or updates it, if already exists.")
    @Override
    //@Tag(name = "Save a instance")
    public ResponseEntity<T> saveInstance(
            @RequestBody E instance) {
        T instanceDTO = service.saveInstance(instance);

        if (instanceDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(instanceDTO, HttpStatus.CREATED);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated or saved the instance",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PutMapping("/{id}")
    @Operation(summary = "Updates an instance of an entity if the instance exists or creates a new one.")
    @Override
    //@Tag(name = "Updates a instance")
    public ResponseEntity<T> updateInstance(@Parameter(description = "id of the instance to be updated", example = "123")
                                            @PathVariable K id,
                                            @RequestBody E instance) {
        return ResponseEntity.ok(service.saveInstance(instance));
    }
}
