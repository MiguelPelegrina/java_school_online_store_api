package com.java_school.final_task.domain.user_role;

import com.java_school.final_task.domain.role.RoleEntity;
import com.java_school.final_task.domain.userRole.UserRoleEntity;
import com.java_school.final_task.domain.userRole.dto.UserRoleDTO;
import com.java_school.final_task.domain.userRole.impl.UserRoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for {@link UserRoleServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTests {
    @InjectMocks
    private UserRoleServiceImpl service;

    private UserRoleEntity instance;

    @BeforeEach
    public void setUp(){
        instance = UserRoleEntity.builder()
                .assignedDate(LocalDate.now())
                .id(1)
                .role(RoleEntity.builder()
                        .name("ADMIN")
                        .build())
                .build();
    }

    @Test
    public void RoleService_GetEntityId_ReturnsIdClass(){
        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(instance.getId(), entityId);
    }

    @Test
    public void RoleService_GetDTOClass_ReturnsDTOClass(){
        // Assert
        assertEquals(service.getDTOClass(), UserRoleDTO.class);
    }
}
