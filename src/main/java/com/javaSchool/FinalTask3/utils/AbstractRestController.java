package com.javaSchool.FinalTask3.utils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO Try to add something that guaranties that the service is never null, if possible.
// TODO What information do I specify in the HttpHeaders?
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
public abstract class AbstractRestController<Entity, EntityDTO, EntityID> {
    protected final AbstractService<Entity, EntityDTO, EntityID> service;

    /**
     * Submits a GET request to obtain all instance of the entity from the database.
     * @return Returns a ResponseEntity with all the DTOs of the instances and the status of the GET request.
     * If successful, the code is 200.
     */
    @GetMapping
    public ResponseEntity<List<EntityDTO>> getAllInstances(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    /**
     * Submits a GET request to get details of a specified instance of the entity from the database.
     * @param id ID of the instance that is searched.
     * @return Returns a ResponseEntity with the DTO of the instance and the status of the GET request.
     * If successful, the code is 200.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityDTO> getInstanceById(@PathVariable EntityID id){
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    /**
     * Submits a POST request with a RequestBody to create an instance of the entity in the database.
     * @param instance Instance to create.
     * @return Returns a ResponseEntity with the DTO of the instance and the status of the POST request.
     * If successful, the code is 200 created successfully, 204 otherwise.
     */
    @PostMapping
    public ResponseEntity<EntityDTO> saveInstance(@RequestBody Entity instance){
        EntityDTO itemDTO = service.saveInstance(instance);

        if (itemDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(itemDTO);
            // return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);
        }
    }

    // TODO What kind of ResponseEntity do I return if an instance is deleted?
    /**
     * Submits a DELETE request to delete an existing instance of the entity from the database.
     * @param id Identifier (ID) of the instance that will be deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable EntityID id){
        // TODO Try - catch with HibernateException/SQLException?
        service.deleteInstance(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
