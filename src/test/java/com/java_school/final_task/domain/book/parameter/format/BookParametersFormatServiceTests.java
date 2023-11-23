package com.java_school.final_task.domain.book.parameter.format;

import com.java_school.final_task.domain.book.parameter.format.impl.BookParametersFormatServiceImpl;
import com.java_school.final_task.mothers.book.parameter.format.BookParametersFormatMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link BookParametersFormatServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class BookParametersFormatServiceTests {
    // Fields
    @Mock
    private BookParametersFormatRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookParametersFormatServiceImpl service;

    private BookParametersFormatEntity instance;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = BookParametersFormatMother.createBookParametersFormat();
    }

    @Test
    public void BookParametersFormatService_GetEntityId_ReturnsIdClass(){
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("Format", entityId);
    }

    @Test
    public void BookParametersFormatService_GetDTOClass_ReturnsDTOClass(){
        // Act
        Class<BookParametersFormatDTO> result = service.getDTOClass();

        // Assert
        assertEquals(BookParametersFormatDTO.class, result);
    }
}
