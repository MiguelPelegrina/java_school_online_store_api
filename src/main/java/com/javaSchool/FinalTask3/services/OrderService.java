package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.OrderDTO;
import com.javaSchool.FinalTask3.entities.Order;
import com.javaSchool.FinalTask3.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository repository;

    private final ModelMapper modelMapper;

    public List<OrderDTO> getAllOrders(){
        return repository.findAll().stream().map((order) -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }

    public  OrderDTO getOrderById(int id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), OrderDTO.class);
    }

    @Transactional
    public OrderDTO saveOrder(Order order){
        return modelMapper.map(repository.save(order), OrderDTO.class);
    }

    @Transactional
    public OrderDTO updateOrder(int id, Order order){
        return repository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setUser(order.getUser());
                    existingOrder.setDeliveryMethod(order.getDeliveryMethod());
                    existingOrder.setOrderStatus(order.getOrderStatus());
                    existingOrder.setPaymentMethod(order.getPaymentMethod());
                    existingOrder.setPaymentStatus(order.getPaymentStatus());
                    existingOrder.setDate(order.getDate());
                    return  modelMapper.map(repository.save(existingOrder), OrderDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteOrder(int id){
        repository.deleteById(id);
    }
}
