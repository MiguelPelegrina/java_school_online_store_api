package com.java_school.final_task.domain.book;

import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.genre.BookGenreDTO;
import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.domain.book.impl.BookServiceImpl;
import com.java_school.final_task.domain.book.parameter.BookParameterDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterRepository;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatDTO;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link BookServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookParameterRepository bookParameterRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookServiceImpl service;

    private BookEntity instance;
    private BookDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = BookEntity.builder()
                .id(1)
                .title("Title")
                .active(true)
                .genre(new BookGenreEntity("Genre"))
                .image("Image")
                .isbn("ISBN")
                .price(new BigDecimal("1.23"))
                .stock(10)
                .parameters(
                        BookParameterEntity.builder()
                                .author("Author")
                                .format(new BookParametersFormatEntity("Format"))
                                .isActive(true)
                                .build()
                )
                .build();

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
    public void BookService_GetEntityId_ReturnsIdClass(){
        // Act
        int entityId = service.getEntityId(instance);

        // Assert
        assertEquals(1, entityId);
    }

    @Test
    public void BookService_CreateBook_ReturnsSavedBookDTO(){
        // Arrange
        when(bookRepository.save(any(BookEntity.class))).thenReturn(instance);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        BookDTO savedInstance = service.saveInstance(instance);

        // Assert
        assertThat(savedInstance).isNotNull();
        verify(bookRepository, times(1)).save(instance);
        verify(modelMapper, times(1)).map(instance, BookDTO.class);
        assertEquals(instanceDTO, savedInstance);
    }

    @Test
    public void BookService_GetAllBooksFiltered_ReturnsBookDTOPage(){
        // Arrange
        final QBookEntity qInstance = QBookEntity.bookEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        List<BookEntity> instances = new ArrayList<>();
        instances.add(instance);

        Page<BookEntity> page = new PageImpl<>(instances);

        queryBuilder.and(qInstance.active.eq(true));
        queryBuilder.and(qInstance.genre.name.containsIgnoreCase(instance.getGenre().getName()));

        BookRequest request = new BookRequest();
        request.setGenre("Genre");
        request.setActive(Optional.of(true));
        request.setPage(0);
        request.setSize(10);
        request.setSortType("ASC");
        request.setSortProperty("title");

        PageRequest pageRequest = PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.Direction.valueOf(request.getSortType()),
                request.getSortProperty()
        );

        when(bookRepository.findAll(queryBuilder, pageRequest)).thenReturn(page);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        Page<BookDTO> resultDTOs = service.getAllInstances(request);

        // Assert
        verify(bookRepository, times(1)).findAll(queryBuilder, pageRequest);
        verify(modelMapper, times(1)).map(instance, service.getDTOClass());
        assertThat(resultDTOs).isNotNull();
        assertThat(resultDTOs).hasSize(1);
        assertThat(resultDTOs.getContent().get(0)).isEqualTo(instanceDTO);
    }
}
