package com.java_school.final_task.domain.book.parameter;

import com.java_school.final_task.domain.book.parameter.impl.BookParameterServiceImpl;
import mothers.book.parameter.BookParameterMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link BookParameterServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class BookParameterServiceTests {
    // Fields
    @Mock
    private BookParameterRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookParameterServiceImpl service;

    private BookParameterEntity instance;

    @BeforeEach
    public void setUp() {
        instance = BookParameterMother.createBookParameter();
    }

    @Test
    void BookParameterService_GetEntityId_ReturnsIdClass() {
        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(1, entityId);
    }

    @Test
    void BookParameterService_GetDTOClass_ReturnsDTOClass() {
        // Act
        Class<BookParameterDTO> result = service.getDTOClass();

        // Assert
        assertEquals(BookParameterDTO.class, result);
    }
}
