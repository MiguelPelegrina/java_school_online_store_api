package com.java_school.final_task.domain.book.genre;

import com.java_school.final_task.domain.book.genre.impl.BookGenreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link BookGenreServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class BookGenreServiceTests {
    // Fields
    @Mock
    private BookGenreRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookGenreServiceImpl service;

    private BookGenreEntity instance;
    private BookGenreDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = BookGenreEntity.builder()
                .name("Genre")
                .build();

        instanceDTO = BookGenreDTO.builder()
                .name("Genre")
                .build();
    }

    @Test
    public void BookGenreService_GetEntityId_ReturnsIdClass(){
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("Genre", entityId);
    }

    @Test
    public void BookGenreService_GetDTOClass_ReturnsDTOClass(){
        // Act
        Class<BookGenreDTO> result = service.getDTOClass();

        // Assert
        assertEquals(BookGenreDTO.class, result);
    }
}
