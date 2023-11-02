package com.javaSchool.FinalTask3.utils;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * The {@code AbstractService} interface defines common service methods for managing instances of an entity. It is designed
 * to handle various HTTP request operations, such as GET, POST, and DELETE, for interacting with entity instances in the
 * application's data source.
 * @param <Entity>    The entity type that this service manages.
 * @param <EntityDTO> The Data Transfer Object (DTO) representing the entity.
 * @param <EntityID>  The type used as the unique identifier for entity instances.
 */
public interface AbstractService<Entity, EntityDTO, EntityID> {
    /**
     * Handles the GET request. Obtains all instances of the entity from the database and returns them as a collection
     * of DTOs.
     * @return Returns a {@link List} of all DTOs.
     */
    List<EntityDTO> getAllInstances();

    /**
     * Handles the GET request with a specified ID. Obtains the instance of the entity with the specified ID from the
     * database and returns it as a DTO.
     * @param id ID of the instance that will be searched.
     * @return Returns the found instance. If no instance is found, it returns null.
     */
    EntityDTO getInstanceById(EntityID id);

    /**
     * Handles the POST request. Saves an instance of the entity into the database.
     * @param instance Instance of the entity that will be saved.
     * @return Returns the DTO. If the instance could not be saved, it returns null.
     */
    EntityDTO saveInstance(Entity instance);

    /**
     * Handles the DELETE request. Deletes the specified instance of the entity, identified by its ID, from the database.
     * @param id ID of the instance that will be deleted.
     */
    void deleteInstance(EntityID id);
}
