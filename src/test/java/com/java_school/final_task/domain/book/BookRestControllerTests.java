package com.java_school.final_task.domain.book;

import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.impl.BookRestControllerImpl;
import com.java_school.final_task.domain.book.impl.BookServiceImpl;
import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for {@link BookRestControllerImpl}
 */
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl service;

    private BookDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instanceDTO = BookDTO.builder()
                .id(1)
                .title("Title")
                .active(true)
                .genre(new BookGenreDTO("Genre"))
                .image("Image")
                .isbn("ISBN")
                .price(new BigDecimal("1.23"))
                .stock(10)
                .parameters(
                        BookParameterDTO.builder()
                                .author("Author")
                                .format(new BookParametersFormatDTO("Hardcover"))
                                .isActive(true)
                                .build()
                )
                .build();
    }

    @Test
    public void BookRestController_GetAllBooksByParams_ReturnBookDTOPage() throws Exception {
        // Arrange
        Page<BookDTO> page = new PageImpl<>(Collections.singletonList(instanceDTO));

        BookRequest request = new BookRequest();
        request.setName("Title");
        request.setGenre("Genre");
        request.setActive(Optional.of(true));
        request.setPage(0);
        request.setSize(10);
        request.setSortType("ASC");
        request.setSortProperty("title");

        when(service.getAllInstances(any(BookRequest.class))).thenReturn(page);

        // Act
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                .param("active", "true")
                .param("name", "Title")
                .param("genre", "Genre")
                .param("page", "0")
                .param("size", "10")
                .param("sortType", "ASC")
                .param("sortProperty", "title"));

        // Assert
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.totalElements").value(page.getTotalElements()))
                .andExpect(jsonPath("$.content[0].title").value("Title"));
    }
}
