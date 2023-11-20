package com.java_school.final_task.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The {@code AbstractRestController} interface serves as a contract for defining common RESTful operations for managing
 * instances of an entity. These operations include retrieving all instances, fetching details of a specific instance,
 * creating new instances, updating existing instances, and deleting instances.
 * @param <E> The entity type for which RESTful operations are defined.
 * @param <T> The Data Transfer Object (DTO) representing the entity.
 * @param <K> The identifier type used for entity instances.
 */
public interface AbstractRestController<E, T, K> {
    /**
     * Submits a GET request to obtain all instance of the entity from the database.
     * @return Returns a ResponseEntity with all the DTOs of the instances and the status of the GET request.
     * If successful, the code is 200.
     */
    ResponseEntity<List<T>> getAllInstances();

    /**
     * Submits a GET request to get details of a specified instance of the entity from the database.
     * @param id ID of the instance that is searched.
     * @return Returns a ResponseEntity with the DTO of the instance and the status of the GET request.
     * If successful, the code is 200.
     */
    ResponseEntity<T> getInstanceById(@PathVariable K id);

    /**
     * Submits a POST request with a RequestBody to create an instance of the entity in the database.
     * @param instance Instance to create.
     * @return Returns a ResponseEntity with the DTO of the instance and the status of the POST request.
     * If successful, the code is 200 created successfully, 204 otherwise.
     */
    ResponseEntity<T> saveInstance(@RequestBody E instance);

    /**
     * Submits a PUT request to update an existing instance of the entity in the database.
     * @param id Identifier (ID) of the instance that will be updated.
     * @param instance Instance with the updated values.
     * @return Returns a ResponseEntity with the DTO of the update instance and the status of the PUT request.
     * If successful, the code is 200.
     */
    ResponseEntity<T> updateInstance(@PathVariable K id, @RequestBody E instance);

    /**
     * Submits a DELETE request to delete an existing instance of the entity from the database.
     *
     * @param id Identifier (ID) of the instance that will be deleted.
     */
    ResponseEntity<?> deleteInstance(@PathVariable K id);
}
