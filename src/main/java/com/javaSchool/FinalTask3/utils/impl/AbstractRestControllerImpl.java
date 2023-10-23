package com.javaSchool.FinalTask3.utils.impl;

import com.javaSchool.FinalTask3.utils.AbstractRestController;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO Try to add something that guaranties that the service is never null, if possible.
/**
 * Parent controller responsible for the interaction between service and user. Obtains data from the services and
 * returns a {@link ResponseEntity} that contains the Data Transfer Object (DTO) of the entity and the status of the
 * operation. Used for entities that only have one attribute and therefore can't be updated.
 * @param <Entity> Entity instance that will be managed.
 * @param <EntityDTO> Data Transfer Object (DTO) of the managed entity instance.
 * @param <EntityID> Identifier of the entity instance.
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@RestController
public abstract class AbstractRestControllerImpl<Entity, EntityDTO, EntityID> implements AbstractRestController<Entity, EntityDTO, EntityID> {
    protected final AbstractServiceImpl<Entity, EntityDTO, EntityID> service;

    @Override
    public ResponseEntity<List<EntityDTO>> getAllInstances(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EntityDTO> getInstanceById(@PathVariable EntityID id){
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EntityDTO> saveInstance(@RequestBody Entity instance){
        EntityDTO instanceDTO = service.saveInstance(instance);

        if (instanceDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(instanceDTO);
        }
    }

    @Override
    public ResponseEntity<EntityDTO> updateInstance(@PathVariable EntityID id, @RequestBody Entity instance){
        return ResponseEntity.ok(service.saveInstance(instance));
    }

    @Override
    public ResponseEntity<?> deleteItem(@PathVariable EntityID id){
        service.deleteInstance(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
