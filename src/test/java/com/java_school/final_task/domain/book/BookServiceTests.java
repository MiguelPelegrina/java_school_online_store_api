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
import org.mockito.Mockito;
import org.mockito.Spy;
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

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Spy
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
        instance = BookEntity.builder()
                .title("Title")
                .active(true)
                .genre(new BookGenreEntity("Genre"))
                .image("String")
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
                .title("The Lord of the Rings")
                .active(true)
                .genre(new BookGenreDTO("Fantasy"))
                .image("MockedString")
                .isbn("MockISBN")
                .price(new BigDecimal("1.23"))
                .stock(10)
                .parameters(
                        BookParameterDTO.builder()
                                .author("Tolkien")
                                .format(new BookParametersFormatDTO("Hardcover"))
                                .isActive(true)
                                .build()
                )
                .build();
    }

    @Test
    public void BookService_CreateBook_ReturnsSavedBookDTO(){
        // Arrange
        when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(instance);
        when(modelMapper.map(instance, service.getDTOClass())).thenReturn(instanceDTO);

        // Act
        BookDTO savedBook = service.saveInstance(instance);

        // Assert
        assertThat(savedBook).isNotNull();
        verify(bookRepository, times(1)).save(instance);
        verify(modelMapper, times(1)).map(instance, BookDTO.class);
        assertEquals(instanceDTO, savedBook);
    }

    @Test
    public void BookService_GetAllBooksFiltered_ReturnsBookDTOPage(){
        // Arrange
        final QBookEntity qInstance = QBookEntity.bookEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        List<BookEntity> instances = new ArrayList<>();
        instances.add(instance);

        Page<BookEntity> pageEntities = new PageImpl<>(instances);

        queryBuilder.and(qInstance.active.eq(true));

        BookRequest request = new BookRequest();
        request.setActive(Optional.of(true));
        request.setPage(0);
        request.setSize(10);
        request.setSortType("ASC");
        request.setSortProperty("title");

        PageRequest pageRequest = PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.Direction.valueOf(request.getSortType()),
                request.getSortProperty());

        when(bookRepository.findAll(queryBuilder, pageRequest)).thenReturn(pageEntities);
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
