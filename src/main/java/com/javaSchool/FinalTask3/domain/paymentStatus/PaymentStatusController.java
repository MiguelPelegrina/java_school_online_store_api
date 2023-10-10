package com.javaSchool.FinalTask3.domain.paymentStatus;

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

@RequestMapping(path = "paymentstatuses")
@RequiredArgsConstructor
@RestController
public class PaymentStatusController {
    private final PaymentStatusService service;

    @GetMapping
    public ResponseEntity<List<PaymentStatusDTO>> getAllPaymentStatuses(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PaymentStatusDTO> getPaymentStatus(@PathVariable String name){
        return new ResponseEntity<>(service.getInstanceById(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentStatusDTO> savePaymentStatus(@RequestBody PaymentStatusEntity paymentStatus){
        PaymentStatusDTO savedPaymentStatus = service.saveInstance(paymentStatus);

        if (savedPaymentStatus == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(savedPaymentStatus, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<PaymentStatusDTO> updatePaymentStatus(@PathVariable String name, @RequestBody PaymentStatusEntity paymentStatus){
        return new ResponseEntity<>(service.updateInstance(name, paymentStatus), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deletePaymentMethod(String name){
        service.deleteInstance(name);
    }
}
