package com.javaSchool.FinalTask3.controllers;

import com.javaSchool.FinalTask3.dtos.PaymentMethodDTO;
import com.javaSchool.FinalTask3.entities.PaymentMethod;
import com.javaSchool.FinalTask3.services.PaymentMethodService;
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

@RequestMapping(name = "paymentmethods")
@RequiredArgsConstructor
@RestController
public class PaymentMethodController {
    private final PaymentMethodService service;

    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> getAllPaymentMethods(){
        return new ResponseEntity<>(service.getAllPaymentMethods(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById(@PathVariable String name){
        return new ResponseEntity<>(service.getPaymentMethodById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentMethodDTO> savePaymentMethod(@RequestBody PaymentMethod paymentMethod){
        PaymentMethodDTO savedPaymentMethod = service.savePaymentMethod(paymentMethod);

        if (savedPaymentMethod == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedPaymentMethod, HttpStatus.OK);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<PaymentMethodDTO> updatedPaymentMethod(@PathVariable String name, @RequestBody PaymentMethod paymentMethod){
        return new ResponseEntity<>(service.updatePaymentMethod(name, paymentMethod), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deletePaymentMethod(@PathVariable String name){
        service.deletePaymentMethod(name);
    }
}
