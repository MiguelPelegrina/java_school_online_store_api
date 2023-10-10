package com.javaSchool.FinalTask3.utils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// TODO Try to add something that guaranties that the modelMapper and the repository are never null, if possible.
/**
 * Parent service responsible for the interaction between repositories and controller. Obtains data from the
 * repository and returns the instance(s) of the entity as Data Transfer Object(s) (DTO) to the related controller.
 * Used for entities that only have one attribute and therefore can't be updated.
 * @param <Entity> Entity instance that will be managed.
 * @param <EntityDTO> Data Transfer Object (DTO) of the managed entity instance.
 * @param <EntityID> Identifier of the entity instance.
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public abstract class AbstractService<Entity, EntityDTO, EntityID> {
    protected final JpaRepository<Entity, EntityID> repository;

    protected final ModelMapper modelMapper;

    /**
     * Handles the GET request. Obtains all instances of the entity from the database and returns them as a collection
     * of DTOs.
     * @return Returns a {@link List} of all DTOs.
     */
    public List<EntityDTO> getAllInstances() {
        return repository.findAll()
                .stream()
                .map(entity ->
                        modelMapper.map(entity, getDTOClass()))
                .collect(Collectors.toList());
    }

    /**
     * Handles the GET request with a specified ID. Obtains the instance of the entity with the specified ID from the
     * database and returns it as a DTO.
     * @param id ID of the instance that will be searched.
     * @return Returns the found instance. If no instance is found, it returns null.
     */
    public EntityDTO getInstanceById(EntityID id){
        return modelMapper.map(repository.findById(id).orElse(null), getDTOClass());
    }

    /**
     * Handles the POST request. Saves an instance of the entity into the database.
     * @param instance Instance of the entity that will be saved.
     * @return Returns the DTO. If the instance could not be saved, it returns null.
     */
    @Transactional
    public EntityDTO saveInstance(Entity instance){
        return modelMapper.map(repository.save(instance), getDTOClass());
    }

    /**
     * Handles the DELETE request. Deletes the specified instance of the entity, identified by its ID, from the database.
     * @param id ID of the instance that will be deleted.
     */
    @Transactional
    public void deleteInstance(EntityID id){
        repository.deleteById(id);
    }

    /**
     * Abstract method to be implemented by subclasses to specify the DTO class that will be used.
     * @return Returns the DTO class of the entity
     */
    protected abstract Class<EntityDTO> getDTOClass();
}
