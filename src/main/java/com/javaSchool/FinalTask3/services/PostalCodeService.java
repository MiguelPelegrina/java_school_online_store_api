package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.PostalCodeDTO;
import com.javaSchool.FinalTask3.entities.PostalCode;
import com.javaSchool.FinalTask3.repositories.PostalCodeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostalCodeService {
    private final PostalCodeRepository repository;

    private final ModelMapper modelMapper;

    public List<PostalCodeDTO> getAllPostalCodes(){
        List<PostalCode> postalCodes = repository.findAll();

        return postalCodes.stream().map(postalCode -> modelMapper.map(postalCode, PostalCodeDTO.class)).collect(Collectors.toList());
    }

    public PostalCodeDTO getPostalCodeById(String code){
        PostalCode postalCode = repository.findById(code).orElse(null);

        return modelMapper.map(postalCode, PostalCodeDTO.class);
    }

    @Transactional
    public PostalCodeDTO savePostalCode(PostalCode postalCode){
        PostalCode savedPostalCode = repository.save(postalCode);

        return modelMapper.map(savedPostalCode, PostalCodeDTO.class);
    }

    @Transactional
    public PostalCodeDTO updatePostalCode(String code, PostalCode postalCode){
        PostalCode existingPostalCode = repository.findById(code).orElse(null);

        if(existingPostalCode != null){
            existingPostalCode.setActive(postalCode.isActive());
            existingPostalCode.setCity(postalCode.getCity());

            PostalCode savedPostalCode = repository.save(existingPostalCode);

            return modelMapper.map(savedPostalCode, PostalCodeDTO.class);
        } else {
            return null;
        }
    }

    @Transactional
    public void deletePostalCode(String code) {
        repository.deleteById(code);
    }
}
