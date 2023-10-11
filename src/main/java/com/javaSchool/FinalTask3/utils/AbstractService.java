package com.javaSchool.FinalTask3.utils;

import com.javaSchool.FinalTask3.exception.ResourceConflictException;
import com.javaSchool.FinalTask3.exception.ResourceNotFoundException;
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

    // TODO Bad practice. Use placeholder instead
    /**
     * Handles the GET request with a specified ID. Obtains the instance of the entity with the specified ID from the
     * database and returns it as a DTO.
     * @param id ID of the instance that will be searched.
     * @return Returns the found instance. If no instance is found, it returns null.
     */
    public EntityDTO getInstanceById(EntityID id){
        return modelMapper.map(repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Instance " + id + " not found ")), getDTOClass());
    }

    // TODO Having a method implementation that does not work properly for all child classes does not seem right. At the
    //  same time though, I don't want to use an interface just to duplicate this line of code for all classes that
    //  don't have relations with other entities. Creating another intermediate parent/child class, would imply that I
    //  need to have a list of repositories, to access every related entity and for each of it I would need to load the
    //  instance
    // TODO Bad practice. Use placeholder.
    /**
     * Handles the POST request. Saves an instance of the entity into the database.
     * @param instance Instance of the entity that will be saved.
     * @return Returns the DTO. If the instance could not be saved, it returns null.
     */
    @Transactional
    public EntityDTO saveInstance(Entity instance){
        // TODO Does not work
        if (repository.existsById(getEntityId(instance))){
            throw new ResourceConflictException("ID is already taken " + getEntityId(instance));
        }
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

    protected abstract EntityID getEntityId(Entity instance);
}
