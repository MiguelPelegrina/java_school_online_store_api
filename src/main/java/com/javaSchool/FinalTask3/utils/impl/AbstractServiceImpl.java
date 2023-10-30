package com.javaSchool.FinalTask3.utils.impl;

import com.javaSchool.FinalTask3.exception.ResourceNotFoundException;
import com.javaSchool.FinalTask3.utils.AbstractService;
import com.javaSchool.FinalTask3.utils.StringValues;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO Try to add something that guaranties that the modelMapper and the repository are never null, if possible.
/**
 * The {@code AbstractServiceImpl} is a parent service responsible for the interaction between repositories and controller.
 * Obtains data from the repository and returns the instance(s) of the entity as Data Transfer Object(s) (DTO) to the
 * related controller. Used for entities that only have one attribute and therefore can't be updated.
 * @param <Entity> Entity instance that will be managed.
 * @param <EntityDTO> Data Transfer Object (DTO) of the managed entity instance.
 * @param <EntityID> Identifier of the entity instance.
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public abstract class AbstractServiceImpl<Entity, EntityDTO, EntityID>
        implements AbstractService<Entity, EntityDTO, EntityID> {
    protected final JpaRepository<Entity, EntityID> repository;

    protected final ModelMapper modelMapper;

    @Override
    public List<EntityDTO> getAllInstances(
                /*@RequestParam("id") Optional<EntityID> id,
                @RequestParam("name") Optional<String> name,
                @RequestParam("active") Optional<Boolean> active,
                @RequestParam("sort") Optional<String> sort,
                @RequestParam("page") Optional<Integer> page,
                @RequestParam("size") Optional<Integer> size*/
            ) {

        return repository.findAll()
                .stream()
                .map(entity ->
                        modelMapper.map(entity, getDTOClass()))
                .collect(Collectors.toList());
    }

    @Override
    public EntityDTO getInstanceById(EntityID id){
        return modelMapper.map(repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(String.format(StringValues.INSTANCE_NOT_FOUND, id))), getDTOClass());
    }

    @Override
    @Transactional
    public EntityDTO saveInstance(Entity instance){
        return modelMapper.map(repository.save(instance), getDTOClass());
    }

    @Override
    @Transactional
    public void deleteInstance(EntityID id){
        repository.deleteById(id);
    }

    /**
     * Abstract method to be implemented by subclasses to specify the DTO class that will be used.
     * @return Returns the DTO class of the entity
     */
    protected abstract Class<EntityDTO> getDTOClass();

    /**
     * Abstract method to be implemented by subclasses to obtain the Identifier of the entity.
     * @param instance Instance of the entity.
     * @return Returns the Identifier of the entity.
     */
    protected abstract EntityID getEntityId(Entity instance);
}
