package com.java_school.final_task.utils.impl;

import com.java_school.final_task.exception.ResourceNotFoundException;
import com.java_school.final_task.utils.AbstractService;
import com.java_school.final_task.utils.StringValues;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code AbstractServiceImpl} is a parent service responsible for the interaction between repositories and controller.
 * Obtains data from the repository and returns the instance(s) of the entity as Data Transfer Object(s) (DTO) to the
 * related controller. Used for entities that only have one attribute and therefore can't be updated.
 *
 * @param <R> Repository of the entity.
 * @param <E> Entity instance that will be managed.
 * @param <T> Data Transfer Object (DTO) of the managed entity instance.
 * @param <K> Identifier of the entity instance.
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public abstract class AbstractServiceImpl<R extends JpaRepository<E, K>, E, T, K>
        implements AbstractService<E, T, K> {
    // Fields
    protected final R repository;
    protected final ModelMapper modelMapper;

    @Override
    public List<T> getAllInstances() {
        return repository.findAll()
                .stream()
                .map(entity ->
                        modelMapper.map(entity, getDTOClass()))
                .collect(Collectors.toList());
    }

    @Override
    public T getInstanceById(K id) {
        return modelMapper.map(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(StringValues.INSTANCE_NOT_FOUND, id))), getDTOClass());
    }

    @Override
    @Transactional
    public T saveInstance(E instance) {
        return modelMapper.map(repository.save(instance), getDTOClass());
    }

    @Override
    @Transactional
    public void deleteInstance(K id) {
        repository.deleteById(id);
    }
}
