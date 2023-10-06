package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.DeliveryMethodDTO;
import com.javaSchool.FinalTask3.entities.DeliveryMethod;
import com.javaSchool.FinalTask3.repositories.DeliveryMethodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DeliveryMethodService {
    private final DeliveryMethodRepository repository;

    private final ModelMapper modelMapper;

    public List<DeliveryMethodDTO> getAllDeliveryMethods(){
        return repository.findAll()
                .stream()
                .map((deliveryMethod) ->
                        modelMapper.map(deliveryMethod, DeliveryMethodDTO.class))
                .collect(Collectors.toList());
    }

    public DeliveryMethodDTO getDeliveryMethodById(String name){
        return modelMapper.map(repository.findById(name)
                .orElse(null), DeliveryMethodDTO.class);
    }

    @Transactional
    public DeliveryMethodDTO saveDeliveryMethod(DeliveryMethod deliveryMethod){
        return modelMapper.map(repository.save(deliveryMethod), DeliveryMethodDTO.class);
    }

    @Transactional
    public DeliveryMethodDTO updateDeliveryMethod(String name, DeliveryMethod deliveryMethod){
        return repository.findById(name)
                .map(existingDeliveryMethod -> {
                    existingDeliveryMethod.setActive(deliveryMethod.isActive());
                    return modelMapper.map(repository.save(existingDeliveryMethod), DeliveryMethodDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteDeliveryMethod(String name){
        repository.deleteById(name);
    }
}
