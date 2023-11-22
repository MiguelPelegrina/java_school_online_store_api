package com.java_school.final_task.domain.user.address;

import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.user.userAddress.UserAddressEntity;
import com.java_school.final_task.domain.user.userAddress.impl.UserAddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserAddressServiceTests {
    @InjectMocks
    private UserAddressServiceImpl service;

    @Test
    public void UserAddressService_GetEntityId_ReturnsIdClass(){
        // Arrange
        UserAddressEntity instance = new UserAddressEntity();
        instance.setId(1);

        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(1, entityId);
    }

    @Test
    public void UserAddressService_GetDTOClass_ReturnsDTOClass(){
        // Act
        Class<UserAddressDTO> result = service.getDTOClass();

        // Assert
        assertEquals(UserAddressDTO.class, result);
    }
}
