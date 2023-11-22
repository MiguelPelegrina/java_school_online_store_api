package com.java_school.final_task.domain.book.parameter;

import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;
import com.java_school.final_task.domain.book.parameter.impl.BookParameterServiceImpl;
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
public class BookParameterServiceTests {
    // Fields
    @Mock
    private BookParameterRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookParameterServiceImpl service;

    private BookParameterEntity instance;
    private BookParameterDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        instance = BookParameterEntity.builder()
                .id(1)
                .isActive(true)
                .author("Author")
                .format(BookParametersFormatEntity.builder()
                        .name("Format")
                        .build())
                .build();

        instanceDTO = BookParameterDTO.builder()
                .id(1)
                .isActive(true)
                .author("Author")
                .format(BookParametersFormatDTO.builder()
                        .name("Format")
                        .build())
                .build();
    }

    @Test
    public void BookParameterService_GetEntityId_ReturnsIdClass(){
        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(1, entityId);
    }

    @Test
    public void BookParameterService_GetDTOClass_ReturnsDTOClass(){
        // Act
        Class<BookParameterDTO> result = service.getDTOClass();

        // Assert
        assertEquals(BookParameterDTO.class, result);
    }
}
