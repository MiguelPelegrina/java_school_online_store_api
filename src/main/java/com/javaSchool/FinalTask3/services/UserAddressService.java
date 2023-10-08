package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.UserAddressDTO;
import com.javaSchool.FinalTask3.entities.UserAddress;
import com.javaSchool.FinalTask3.repositories.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserAddressService {
    private final UserAddressRepository repository;

    private final ModelMapper modelMapper;

    public List<UserAddressDTO> getAllUserAddresses(){
        return repository.findAll()
                .stream()
                .map((userAddress) ->
                        modelMapper.map(userAddress, UserAddressDTO.class))
                .collect(Collectors.toList());
    }

    public UserAddressDTO getUserAddressById(int id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), UserAddressDTO.class);
    }

    @Transactional
    public UserAddressDTO saveUserAddress(UserAddress userAddress){
        return modelMapper.map(repository.save(userAddress), UserAddressDTO.class);
    }

    @Transactional
    public UserAddressDTO updateUserAddress(int id, UserAddress userAddress){
        return repository.findById(id).map(existingUserAddress -> {
                    existingUserAddress.setPostalCode(userAddress.getPostalCode());
                    existingUserAddress.setUserId(userAddress.getUserId());
                    existingUserAddress.setStreet(userAddress.getStreet());
                    existingUserAddress.setNumber(userAddress.getNumber());
                    existingUserAddress.setActive(userAddress.isActive());
                    return modelMapper.map(repository.save(existingUserAddress), UserAddressDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteUserAddress(int id){
        repository.deleteById(id);
    }
}
