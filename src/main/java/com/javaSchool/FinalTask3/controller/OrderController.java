package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.OrderDTO;
import com.javaSchool.FinalTask3.entities.Order;
import com.javaSchool.FinalTask3.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("orders")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id){
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody Order order){
        OrderDTO savedOrder = service.saveInstance(order);

        if (savedOrder == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable int id, @RequestBody Order order){
        return new ResponseEntity<>(service.updateInstance(id, order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id){
        service.deleteInstance(id);
    }
}
