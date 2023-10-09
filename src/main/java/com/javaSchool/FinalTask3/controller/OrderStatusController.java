package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.OrderStatusDTO;
import com.javaSchool.FinalTask3.entities.OrderStatus;
import com.javaSchool.FinalTask3.services.OrderStatusService;
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

@RequestMapping(path = "/orderstatuses")
@RequiredArgsConstructor
@RestController
public class OrderStatusController {
    private final OrderStatusService service;

    @GetMapping
    public ResponseEntity<List<OrderStatusDTO>> getAllOrderStatuses(){
        return new ResponseEntity<>(service.getAllOrderStatuses(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<OrderStatusDTO> getOrderStatusById(@PathVariable String name){
        return new ResponseEntity<>(service.getOrderStatusById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderStatusDTO> saveOrderStatus(@RequestBody OrderStatus orderStatus){
        OrderStatusDTO savedOrderStatus = service.saveOrderStatus(orderStatus);

        if(savedOrderStatus == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedOrderStatus, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<OrderStatusDTO> updateOrderStatus(@PathVariable String name, @RequestBody OrderStatus orderStatus){
        return new ResponseEntity<>(service.updateOrderStatus(name, orderStatus), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteOrderStatus(@PathVariable String name){
        service.deleteOrderStatus(name);
    }
}
