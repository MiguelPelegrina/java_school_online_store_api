package com.javaSchool.FinalTask3.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractControllerWithUpdate<Entity, EntityDTO, EntityID> extends AbstractController<Entity, EntityDTO, EntityID> {
    public AbstractControllerWithUpdate(AbstractServiceWithUpdate<Entity, EntityDTO, EntityID> service){
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
        AbstractServiceWithUpdate<Entity, EntityDTO, EntityID> service = (AbstractServiceWithUpdate<Entity, EntityDTO, EntityID>) this.service;
        return new ResponseEntity<>(service.updateInstance(id, instance),HttpStatus.OK);
    }
}
