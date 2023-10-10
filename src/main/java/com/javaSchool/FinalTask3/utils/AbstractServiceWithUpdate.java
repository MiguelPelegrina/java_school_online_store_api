package com.javaSchool.FinalTask3.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Parent service that inherits from {@link AbstractService}. Adds the necessary methods to update an instance of an entity
 * in the database. Used for entities that have more than one attribute and therefore can be updated.
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public abstract class AbstractServiceWithUpdate<Entity, EntityDTO, EntityID> extends AbstractService<Entity, EntityDTO, EntityID> {
    /**
     * All arguments constructor.
     * @param repository Repository of the entity.
     * @param modelMapper ModelMapper that converts the instance of the entity to its related DTO.
     */
    public AbstractServiceWithUpdate(JpaRepository<Entity, EntityID> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    /**
     * Handles the PUT request. Updates an existing instance of the entity in the database.
     * @param id ID of the instance that will be updated.
     * @param item Instance that will be updated in the database.
     * @return Returns the updated instance. If the instance could not be updated, it returns null.
     */
    @Transactional
    public EntityDTO updateInstance(EntityID id, Entity item){
        return repository.findById(id).map(existingItem -> {
            updateValues(existingItem, item);
            return modelMapper.map(repository.save(existingItem), getDTOClass());
        }).orElse(null);
    }

    /**
     * Abstract method to be implemented by subclasses to update existing item properties
     * @param existingInstance Instance that already exists in the database.
     * @param newInstance Instance that stores the value to update the existing instance.
     */
    protected abstract void updateValues(Entity existingInstance, Entity newInstance);
}
