package com.javaSchool.FinalTask3.controllers;

import com.javaSchool.FinalTask3.dtos.DeliveryMethodDTO;
import com.javaSchool.FinalTask3.entities.DeliveryMethod;
import com.javaSchool.FinalTask3.services.DeliveryMethodService;
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

@RequestMapping("deliverymethods")
@RestController
@RequiredArgsConstructor
public class DeliveryMethodController {
    private final DeliveryMethodService service;

    @GetMapping
    public ResponseEntity<List<DeliveryMethodDTO>> getAllDeliveryMethods(){
        return new ResponseEntity<>(service.getAllDeliveryMethods(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DeliveryMethodDTO> getDeliveryMethodByid(@PathVariable String name){
        return new ResponseEntity<>(service.getDeliveryMethodById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DeliveryMethodDTO> saveDeliveryMethod(@RequestBody DeliveryMethod deliveryMethod){
        DeliveryMethodDTO savedDeliveryMethod = service.saveDeliveryMethod(deliveryMethod);

        if (savedDeliveryMethod == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedDeliveryMethod, HttpStatus.OK);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<DeliveryMethodDTO> updateDeliveryMethod(@PathVariable String name, @RequestBody DeliveryMethod deliveryMethod){
        return new ResponseEntity<>(service.updateDeliveryMethod(name, deliveryMethod), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteDeliveryMethod(@PathVariable String name){
        service.deleteDeliveryMethod(name);
    }
}
