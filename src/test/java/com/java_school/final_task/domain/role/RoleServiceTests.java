package com.java_school.final_task.domain.role;

import com.java_school.final_task.domain.role.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link RoleServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class RoleServiceTests {
    @InjectMocks
    private RoleServiceImpl service;

    private RoleEntity instance;

    @BeforeEach
    public void setUp() {
        instance = RoleEntity.builder()
                .name("ROLE")
                .build();
    }

    @Test
    void RoleService_GetEntityId_ReturnsIdClass() {
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals(instance.getName(), entityId);
    }

    @Test
    void RoleService_GetDTOClass_ReturnsDTOClass() {
        // Assert
        assertEquals(service.getDTOClass(), RoleDTO.class);
    }
}
