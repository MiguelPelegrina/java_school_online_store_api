package com.javaSchool.FinalTask3.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The {@code AbstractRestController} interface serves as a contract for defining common RESTful operations for managing
 * instances of an entity. These operations include retrieving all instances, fetching details of a specific instance,
 * creating new instances, updating existing instances, and deleting instances.
 * @param <Entity>    The entity type for which RESTful operations are defined.
 * @param <EntityDTO> The Data Transfer Object (DTO) representing the entity.
 * @param <EntityID>  The identifier type used for entity instances.
 */
public interface AbstractRestController<Entity, EntityDTO, EntityID> {
    /**
     * Submits a GET request to obtain all instance of the entity from the database.
     * @return Returns a ResponseEntity with all the DTOs of the instances and the status of the GET request.
     * If successful, the code is 200.
     */
    ResponseEntity<List<EntityDTO>> getAllInstances(
            /*@RequestParam("id") Optional<EntityID> id,
            @RequestParam("name") Optional<String> name,
            @RequestParam("active") Optional<Boolean> active,
            @RequestParam("sort") Optional<String> sort,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size*/
    );

    /**
     * Submits a GET request to get details of a specified instance of the entity from the database.
     * @param id ID of the instance that is searched.
     * @return Returns a ResponseEntity with the DTO of the instance and the status of the GET request.
     * If successful, the code is 200.
     */
    ResponseEntity<EntityDTO> getInstanceById(@PathVariable EntityID id);

    /**
     * Submits a POST request with a RequestBody to create an instance of the entity in the database.
     * @param instance Instance to create.
     * @return Returns a ResponseEntity with the DTO of the instance and the status of the POST request.
     * If successful, the code is 200 created successfully, 204 otherwise.
     */
    ResponseEntity<EntityDTO> saveInstance(@RequestBody Entity instance);

    /**
     * Submits a PUT request to update an existing instance of the entity in the database.
     * @param id Identifier (ID) of the instance that will be updated.
     * @param instance Instance with the updated values.
     * @return Returns a ResponseEntity with the DTO of the update instance and the status of the PUT request.
     * If successful, the code is 200.
     */
    ResponseEntity<EntityDTO> updateInstance(@PathVariable EntityID id, @RequestBody Entity instance);

    // TODO What kind of ResponseEntity do I return if an instance is deleted?
    /**
     * Submits a DELETE request to delete an existing instance of the entity from the database.
     * @param id Identifier (ID) of the instance that will be deleted.
     */
    ResponseEntity<?> deleteItem(@PathVariable EntityID id);
}
