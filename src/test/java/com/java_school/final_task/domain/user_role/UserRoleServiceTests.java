package com.java_school.final_task.domain.user_role;

import com.java_school.final_task.domain.user_role.dto.UserRoleDTO;
import com.java_school.final_task.domain.user_role.impl.UserRoleServiceImpl;
import mothers.user_role.UserRoleMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for {@link UserRoleServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class UserRoleServiceTests {
    @InjectMocks
    private UserRoleServiceImpl service;

    private UserRoleEntity instance;

    @BeforeEach
    public void setUp() {
        instance = UserRoleMother.createUserRoleAdmin();
    }

    @Test
    void RoleService_GetEntityId_ReturnsIdClass() {
        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(instance.getId(), entityId);
    }

    @Test
    void RoleService_GetDTOClass_ReturnsDTOClass() {
        // Assert
        assertEquals(service.getDTOClass(), UserRoleDTO.class);
    }
}
