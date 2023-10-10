package com.javaSchool.FinalTask3.domain.paymentMethod;

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
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById(@PathVariable String name){
        return new ResponseEntity<>(service.getInstanceById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentMethodDTO> savePaymentMethod(@RequestBody PaymentMethodEntity paymentMethod){
        PaymentMethodDTO savedPaymentMethod = service.saveInstance(paymentMethod);

        if (savedPaymentMethod == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedPaymentMethod, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<PaymentMethodDTO> updatedPaymentMethod(@PathVariable String name, @RequestBody PaymentMethodEntity paymentMethod){
        return new ResponseEntity<>(service.updateInstance(name, paymentMethod), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deletePaymentMethod(@PathVariable String name){
        service.deleteInstance(name);
    }
}
