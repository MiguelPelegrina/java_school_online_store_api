package com.javaSchool.FinalTask3.domain.orderStatus;

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
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<OrderStatusDTO> getOrderStatusById(@PathVariable String name){
        return new ResponseEntity<>(service.getInstanceById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderStatusDTO> saveOrderStatus(@RequestBody OrderStatusEntity orderStatus){
        OrderStatusDTO savedOrderStatus = service.saveInstance(orderStatus);

        if(savedOrderStatus == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedOrderStatus, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<OrderStatusDTO> updateOrderStatus(@PathVariable String name, @RequestBody OrderStatusEntity orderStatus){
        return new ResponseEntity<>(service.updateInstance(name, orderStatus), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteOrderStatus(@PathVariable String name){
        service.deleteInstance(name);
    }
}
