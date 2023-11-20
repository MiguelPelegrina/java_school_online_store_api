package com.java_school.final_task.domain.user_address;

import com.java_school.final_task.domain.user.userAddress.UserAddressDTO;
import com.java_school.final_task.domain.user.userAddress.UserAddressEntity;
import com.java_school.final_task.domain.user.userAddress.impl.UserAddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
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
        Class<UserAddressDTO> result = service.getDTOClass();

        assertEquals(UserAddressDTO.class, result);
    }
}
