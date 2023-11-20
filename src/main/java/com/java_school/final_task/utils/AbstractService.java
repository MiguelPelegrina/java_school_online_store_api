package com.java_school.final_task.utils;

import java.util.List;

/**
 * The {@code AbstractService} interface defines common service methods for managing instances of an entity. It is designed
 * to handle various HTTP request operations, such as GET, POST, and DELETE, for interacting with entity instances in the
 * application's data source.
 * @param <E> The entity type that this service manages.
 * @param <T> The Data Transfer Object (DTO) representing the entity.
 * @param <K> The type used as the unique identifier for entity instances.
 */
public interface AbstractService<E, T, K> {
    /**
     * Handles the GET request. Obtains all instances of the entity from the database and returns them as a collection
     * of DTOs.
     * @return Returns a {@link List} of all DTOs.
     */
    List<T> getAllInstances();

    /**
     * Handles the GET request with a specified ID. Obtains the instance of the entity with the specified ID from the
     * database and returns it as a DTO.
     * @param id ID of the instance that will be searched.
     * @return Returns the found instance. If no instance is found, it returns null.
     */
    T getInstanceById(K id);

    /**
     * Handles the POST request. Saves an instance of the entity into the database.
     * @param instance Instance of the entity that will be saved.
     * @return Returns the DTO. If the instance could not be saved, it returns null.
     */
    T saveInstance(E instance);

    /**
     * Handles the DELETE request. Deletes the specified instance of the entity, identified by its ID, from the database.
     * @param id ID of the instance that will be deleted.
     */
    void deleteInstance(K id);

    /**
     * Needs to be implemented by subclasses to specify the DTO class that will be used.
     * @return Returns the DTO class of the entity
     */
    Class<T> getDTOClass();

    /**
     * Needs to be implemented by subclasses to obtain the Identifier of the entity.
     * @param instance Instance of the entity.
     * @return Returns the Identifier of the entity.
     */
    K getEntityId(E instance);
}
