package com.java_school.final_task.domain.user;

import com.java_school.final_task.domain.role.RoleDTO;
import com.java_school.final_task.domain.role.RoleEntity;
import com.java_school.final_task.domain.user.impl.UserServiceImpl;
import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.user.userAddress.UserAddressEntity;
import com.java_school.final_task.domain.user.userAddress.UserAddressRepository;
import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.PostalCodeEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.CityEntity;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryDTO;
import com.java_school.final_task.domain.user.userAddress.postalCode.city.country.CountryEntity;
import com.java_school.final_task.domain.userRole.UserRoleEntity;
import com.java_school.final_task.domain.userRole.dto.UserRoleJsonDTO;
import com.java_school.final_task.security.JwtUtil;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link UserServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl service;

    private UserEntity instance;
    private UserDTO instanceDTO;

    @BeforeEach
    public void setUp(){
        instance = createUser();

        instanceDTO = createUserDTO();

        prepareRequestAttributes();
    }

    @Test
    public void UserService_GetEntityId_ReturnsIdClass(){
        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(4, entityId);
    }

    @Test
    public void UserService_CreateUser_ReturnsSavedUserDTO(){
        when(userRepository.findById(instance.getId())).thenReturn(Optional.ofNullable(instance));
        when(userRepository.save(any(UserEntity.class))).thenReturn(instance);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        UserDTO savedInstance = service.saveInstance(instance);

        // Assert
        assertThat(savedInstance).isNotNull();
        verify(userRepository, times(1)).save(instance);
        verify(modelMapper, times(1)).map(instance, UserDTO.class);
        assertEquals(instanceDTO, savedInstance);
    }

    @Test
    public void UserService_GetAllClients_ReturnClientsPage(){
        // Arrange
        final QUserEntity qInstance = QUserEntity.userEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        List<UserEntity> instances = new ArrayList<>();
        instances.add(instance);

        Page<UserEntity> page = new PageImpl<>(instances);

        queryBuilder.and(qInstance.roles.any().role.name.eq("CLIENT"));

        UserRequest request = new UserRequest();
        request.setPage(0);
        request.setPage(10);
        request.setSortType("ASC");
        request.setSortProperty("name");

        PageRequest pageRequest = PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.Direction.valueOf(request.getSortType()),
                request.getSortProperty()
        );

        when(userRepository.findById(instance.getId())).thenReturn(Optional.ofNullable(instance));
        when(userRepository.findAll(queryBuilder, pageRequest)).thenReturn(page);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        Page<UserDTO> resultDTOs = service.getAllInstances(request);

        // Assert
        verify(userRepository, times(1)).findAll(queryBuilder, pageRequest);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.getContent().get(0)).isEqualTo(instanceDTO);
    }

    private UserEntity createUser() {
        return UserEntity.builder()
                .id(4)
                .active(true)
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .password("Password")
                .name("Name")
                .surname("Surname")
                .roles(Set.of(UserRoleEntity.builder()
                        .assignedDate(LocalDate.now())
                        .id(1)
                        .role(RoleEntity.builder()
                                .name("ADMIN")
                                .build())
                        .build()))
                .address(UserAddressEntity.builder()
                        .postalCode(PostalCodeEntity.builder()
                                .isActive(true)
                                .code("Code")
                                .city(CityEntity.builder()
                                        .name("City")
                                        .countryName(CountryEntity.builder()
                                                .isActive(true)
                                                .name("Country")
                                                .build())
                                        .isActive(true)
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private UserDTO createUserDTO() {
        return UserDTO.builder()
                .id(4)
                .isActive(true)
                .email("email@.com")
                .dateOfBirth(LocalDate.now())
                .phone("12345678912")
                .name("Name")
                .surname("Surname")
                .roles(Set.of(UserRoleJsonDTO.builder()
                        .role(RoleDTO.builder()
                                .name("ADMIN")
                                .build())
                        .assignedDate(LocalDate.now())
                        .build()))
                .address(UserAddressDTO.builder()
                        .postalCode(PostalCodeDTO.builder()
                                .isActive(true)
                                .code("Code")
                                .city(CityDTO.builder()
                                        .name("City")
                                        .country(CountryDTO.builder()
                                                .isActive(true)
                                                .name("Country")
                                                .build())
                                        .isActive(true)
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private void prepareRequestAttributes() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWd1ZWxAZW1haWwuY29tIiwiaWQiOjQsInJvbGVzIjpbIkFETUlOIl0sImV4cCI6MTkxNjY3Mzk1NX0.wrX_u_broIqIiO-47Z5pZ4hI8zvA40Yj40nAdBCpFJM");

        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);

        try (MockedStatic<JwtUtil> mockedStatic = mockStatic(JwtUtil.class)){
            mockedStatic.when(() -> JwtUtil.getIdFromToken(requestAttributes)).thenReturn(instance.getId());
        }
    }
}
