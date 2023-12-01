package com.java_school.final_task.domain.book.genre;

import com.java_school.final_task.domain.book.genre.impl.BookGenreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link BookGenreServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class BookGenreServiceTests {
    // Fields
    @InjectMocks
    private BookGenreServiceImpl service;

    private BookGenreEntity instance;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = new BookGenreEntity("Genre");
    }

    @Test
    void BookGenreService_GetEntityId_ReturnsIdClass() {
        // Act
        String entityId = service.getEntityId(instance);

        // Assert
        assertEquals("Genre", entityId);
    }

    @Test
    void BookGenreService_GetDTOClass_ReturnsDTOClass() {
        // Act
        Class<BookGenreDTO> result = service.getDTOClass();

        // Assert
        assertEquals(BookGenreDTO.class, result);
    }
}
