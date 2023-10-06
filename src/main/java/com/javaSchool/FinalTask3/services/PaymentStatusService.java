package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.PaymentStatusDTO;
import com.javaSchool.FinalTask3.entities.PaymentStatus;
import com.javaSchool.FinalTask3.repositories.PaymentStatusRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PaymentStatusService {
    private final PaymentStatusRepository repository;

    private final ModelMapper modelMapper;

    public List<PaymentStatusDTO> getAllPaymentStatuses(){
        return repository.findAll()
                .stream()
                .map(paymentStatus ->
                        modelMapper.map(paymentStatus, PaymentStatusDTO.class))
                .collect(Collectors.toList());
    }

    public PaymentStatusDTO getPaymentStatusById(String name){
        return modelMapper.map(repository.findById(name).orElse(null), PaymentStatusDTO.class);
    }

    @Transactional
    public PaymentStatusDTO savePaymentStatus(PaymentStatus paymentStatus){
        return modelMapper.map(repository.save(paymentStatus), PaymentStatusDTO.class);
    }

    @Transactional
    public PaymentStatusDTO updatePaymentStatus(String name, PaymentStatus paymentStatus){
        return repository.findById(name)
                .map(existingPaymentStatus -> {
                    existingPaymentStatus.setActive(paymentStatus.isActive());
                    return modelMapper.map(existingPaymentStatus, PaymentStatusDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deletePaymentMethod(String name){
        repository.deleteById(name);
    }
}
