package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.OrderStatusDTO;
import com.javaSchool.FinalTask3.entities.OrderStatus;
import com.javaSchool.FinalTask3.repositories.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderStatusService {
    private final OrderStatusRepository repository;

    private final ModelMapper modelMapper;

    public List<OrderStatusDTO> getAllOrderStatuses(){
        return repository.findAll()
                .stream()
                .map((orderStatus) ->
                        modelMapper.map(orderStatus, OrderStatusDTO.class))
                .collect(Collectors.toList());
    }

    public OrderStatusDTO getOrderStatusById(String name){
        return modelMapper.map(repository.findById(name)
                .orElse(null), OrderStatusDTO.class);
    }

    @Transactional
    public OrderStatusDTO saveOrderStatus(OrderStatus orderStatus){
        return modelMapper.map(repository.save(orderStatus), OrderStatusDTO.class);
    }

    @Transactional
    public OrderStatusDTO updateOrderStatus(String name, OrderStatus orderStatus){
        return repository.findById(name)
                .map(existingOrderStatus -> {
                    existingOrderStatus.setActive(orderStatus.isActive());
                    return modelMapper.map(repository.save(orderStatus), OrderStatusDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteOrderStatus(String name){
        repository.deleteById(name);
    }
}
