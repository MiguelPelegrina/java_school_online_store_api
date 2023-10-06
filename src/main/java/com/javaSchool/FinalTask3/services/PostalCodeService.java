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
        return repository.findAll()
                .stream()
                .map(postalCode ->
                        modelMapper.map(postalCode, PostalCodeDTO.class))
                .collect(Collectors.toList());
    }

    public PostalCodeDTO getPostalCodeById(String code){
        return modelMapper.map(repository.findById(code)
                .orElse(null), PostalCodeDTO.class);
    }

    @Transactional
    public PostalCodeDTO savePostalCode(PostalCode postalCode){
        return modelMapper.map(repository.save(postalCode), PostalCodeDTO.class);
    }

    @Transactional
    public PostalCodeDTO updatePostalCode(String code, PostalCode postalCode){
        return repository.findById(code)
                .map(existingPostalCode -> {
                    existingPostalCode.setCity(postalCode.getCity());
                    existingPostalCode.setActive(postalCode.isActive());
                    return modelMapper.map(existingPostalCode, PostalCodeDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deletePostalCode(String code) {
        repository.deleteById(code);
    }
}
