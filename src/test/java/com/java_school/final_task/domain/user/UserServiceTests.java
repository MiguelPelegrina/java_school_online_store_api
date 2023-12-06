package com.java_school.final_task.domain.user;

import com.java_school.final_task.domain.user.impl.UserServiceImpl;
import com.java_school.final_task.domain.user.user_address.UserAddressRepository;
import com.java_school.final_task.exception.user.InsufficientPermissionsException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import com.querydsl.core.BooleanBuilder;
import mothers.request.RequestMother;
import mothers.user.UserMother;
import mothers.user_role.UserRoleMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link UserServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl service;

    private UserEntity instance;
    private UserDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        instance = UserMother.createUser();

        instanceDTO = UserMother.createUserDTO();

        RequestMother.prepareRequestAttributes(instance.getId());
    }

    @Test
    void UserService_GetEntityId_ReturnsIdClass() {
        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(4, entityId);
    }

    @Test
    void UserService_CreateUser_ReturnsSavedUserDTO() {
        when(passwordEncoder.encode(instance.getPassword())).thenReturn(
                "$2a$10$I3rJr4zrbsHQ4jGPKcyqKOcstbsLBFCe/7KmXZYnG8VoDMRDlJxC6"
        );
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
    void UserService_GetAllClients_ReturnClientsPage() {
        // Arrange
        final QUserEntity qInstance = QUserEntity.userEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        List<UserEntity> instances = new ArrayList<>();
        instances.add(instance);

        Page<UserEntity> page = new PageImpl<>(instances);

        UserRequest request = RequestMother.createUserRequest();

        PageRequest pageRequest = RequestMother.createPageRequest(request);

        queryBuilder.and(qInstance.name.containsIgnoreCase(request.getName())
                .or(qInstance.surname.containsIgnoreCase(request.getName()))
                .or(qInstance.email.containsIgnoreCase(request.getName())));
        queryBuilder.and(qInstance.roles.any().role.name.eq("CLIENT"));

        when(userRepository.findById(instance.getId())).thenReturn(Optional.ofNullable(instance));
        when(userRepository.findAll(queryBuilder, pageRequest)).thenReturn(page);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        Page<UserDTO> resultDTOs = service.getAllInstances(request);

        // Assert
        verify(userRepository, times(1)).findAll(queryBuilder, pageRequest);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull().hasSize(1);
        assertThat(resultDTOs.getContent().get(0)).isEqualTo(instanceDTO);
    }

    @Test
    void UserService_GetAllClients_ThrowsUserDoesNotExist() {
        // Arrange
        UserRequest request = RequestMother.createUserRequest();

        // Act & assert
        assertThrows(UserDoesNotExistException.class, () -> service.getAllInstances(request));
        verify(userRepository, times(1)).findById(instance.getId());
    }

    @Test
    void UserService_SaveInstance_ThrowsUserDoesNotExist() {
        // Act & assert
        assertThrows(UserDoesNotExistException.class, () -> service.saveInstance(instance));
        verify(userRepository, times(1)).findById(instance.getId());
    }

    @Test
    void UserService_GetAllClients_ThrowsInsufficientPermissions() {
        // Arrange
        instance.setRoles(Set.of(UserRoleMother.createUserRoleClient()));
        UserRequest request = RequestMother.createUserRequest();

        when(userRepository.findById(instance.getId())).thenReturn(Optional.ofNullable(instance));

        // Act & assert
        assertThrows(InsufficientPermissionsException.class, () -> service.getAllInstances(request));
    }
}
