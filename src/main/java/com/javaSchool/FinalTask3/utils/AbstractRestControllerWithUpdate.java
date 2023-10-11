package com.javaSchool.FinalTask3.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Parent controller that inherits from {@link AbstractRestController}. Adds the necessary methods to update an instance
 * of an entity in the database. Used for entities that have more than one attribute and therefore can be updated.
 * @param <Entity> Entity that is managed through a RestController.
 * @param <EntityDTO> Data Transfer Object (DTO) of the entity.
 * @param <EntityID> Identifier of the entity.
 */
public abstract class AbstractRestControllerWithUpdate<Entity, EntityDTO, EntityID> extends AbstractRestController<Entity, EntityDTO, EntityID> {
    /**
     * All arguments constructor.
     * @param service Service of the entity.
     */
    public AbstractRestControllerWithUpdate(AbstractServiceWithUpdate<Entity, EntityDTO, EntityID> service){
        super(service);
    }

    /**
     * Submits a PUT request to update an existing instance of the entity in the database.
     * @param id Identifier (ID) of the instance that will be updated.
     * @param instance Instance with the updated values.
     * @return Returns a ResponseEntity with the DTO of the update instance and the status of the PUT request.
     * If successful, the code is 200.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityDTO> updateInstance(@PathVariable EntityID id, @RequestBody Entity instance){
        if (this.service instanceof AbstractServiceWithUpdate<Entity, EntityDTO, EntityID> service) {
            return new ResponseEntity<>(service.updateInstance(id, instance), HttpStatus.OK);
        } else {
            // TODO Should throw an exception
            return null;
        }
    }
}
