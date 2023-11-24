package com.java_school.final_task.domain.user;

import com.java_school.final_task.domain.user.impl.UserServiceImpl;
import com.java_school.final_task.domain.user.userAddress.UserAddressRepository;
import com.java_school.final_task.exception.user.InsufficientPermissionsException;
import com.java_school.final_task.exception.user.UserDoesNotExistException;
import mothers.user.UserMother;
import com.java_school.final_task.security.JwtUtil;
import com.querydsl.core.BooleanBuilder;
import mothers.user_role.UserRoleMother;
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
        instance = UserMother.createUser();

        instanceDTO = UserMother.createUserDTO();

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

        UserRequest request = generateUserRequest();

        PageRequest pageRequest = generatePageRequest(request);

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
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.getContent().get(0)).isEqualTo(instanceDTO);
    }

    @Test
    public void UserService_GetAllClients_ThrowsUserDoesNotExist(){
        // Arrange
        UserRequest request = generateUserRequest();

        // Act & assert
        assertThrows(UserDoesNotExistException.class, () -> service.getAllInstances(request));
        verify(userRepository, times(1)).findById(instance.getId());
    }

    @Test
    public void UserService_SaveInstance_ThrowsUserDoesNotExist(){
        // Act & assert
        assertThrows(UserDoesNotExistException.class, () -> service.saveInstance(instance));
        verify(userRepository, times(1)).findById(instance.getId());
    }

    @Test
    public void UserService_GetAllClients_ThrowsInsufficientPermissions(){
        // Arrange
        instance.setRoles(Set.of(UserRoleMother.createUserRoleClient()));
        UserRequest request = generateUserRequest();

        when(userRepository.findById(instance.getId())).thenReturn(Optional.ofNullable(instance));

        // Act & assert
        assertThrows(InsufficientPermissionsException.class, () -> service.getAllInstances(request));
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

    private UserRequest generateUserRequest(){
        UserRequest request = new UserRequest();
        request.setName("Name");
        request.setPage(0);
        request.setPage(10);
        request.setSortType("ASC");
        request.setSortProperty("name");
        return request;
    }

    private PageRequest generatePageRequest(UserRequest request){
        return PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.Direction.valueOf(request.getSortType()),
                request.getSortProperty()
        );
    }
}
