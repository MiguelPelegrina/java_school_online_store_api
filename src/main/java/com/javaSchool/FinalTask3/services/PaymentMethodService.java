package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.PaymentMethodDTO;
import com.javaSchool.FinalTask3.entities.PaymentMethod;
import com.javaSchool.FinalTask3.repositories.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PaymentMethodService {
    private final PaymentMethodRepository repository;

    private final ModelMapper modelMapper;

    public List<PaymentMethodDTO> getAllPaymentMethods(){
        return repository.findAll()
                .stream()
                .map((paymentMethod) -> modelMapper
                        .map(paymentMethod, PaymentMethodDTO.class))
                .collect(Collectors.toList());
    }

    public PaymentMethodDTO getPaymentMethodById(String name){
        return modelMapper.map(repository.findById(name)
                .orElse(null), PaymentMethodDTO.class);
    }

    @Transactional
    public PaymentMethodDTO savePaymentMethod(PaymentMethod paymentMethod){
        return modelMapper.map(repository.save(paymentMethod), PaymentMethodDTO.class);
    }

    @Transactional
    public PaymentMethodDTO updatePaymentMethod(String name, PaymentMethod paymentMethod){
        return repository.findById(name)
                .map(existingPaymentMethod -> {
                    existingPaymentMethod.setActive(paymentMethod.isActive());
                    return modelMapper.map(repository.save(existingPaymentMethod), PaymentMethodDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deletePaymentMethod(String name){
        repository.deleteById(name);
    }
}
