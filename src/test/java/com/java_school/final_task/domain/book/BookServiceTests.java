package com.java_school.final_task.domain.book;

import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.impl.BookServiceImpl;
import com.java_school.final_task.domain.book.parameter.BookParameterRepository;
import mothers.book.BookMother;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link BookServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @InjectMocks
    private BookServiceImpl service;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookParameterRepository bookParameterRepository;

    @Mock
    private ModelMapper modelMapper;

    private BookEntity instance;
    private BookDTO instanceDTO;

    @BeforeEach
    public void setUp() {
        // Arrange
        instance = BookMother.createBook();

        instanceDTO = BookMother.createBookDTO();
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
        when(bookRepository.save(instance)).thenReturn(instance);
        when(bookParameterRepository.findByAuthorAndFormat(
                instance.getParameters().getAuthor(),
                instance.getParameters().getFormat()))
                .thenReturn(List.of(instance.getParameters()));
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

        BookRequest request = new BookRequest();
        request.setGenre("Genre");
        request.setName("Title");
        request.setActive(Optional.of(true));
        request.setPage(0);
        request.setSize(10);
        request.setSortType("ASC");
        request.setSortProperty("title");

        queryBuilder.and(qInstance.active.eq(true));
        queryBuilder.and(qInstance.title.containsIgnoreCase("Title")
                        .or(qInstance.parameters.author.containsIgnoreCase("Title")));
        queryBuilder.and(qInstance.genre.name.containsIgnoreCase(instance.getGenre().getName()));

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

    /*@Test
    public void BookService_GetTopProducts_ReturnNumberedBookDTOs(){
        // Arrange
        int limit = 2;
        QOrderBookEntity qOrderBook = QOrderBookEntity.orderBookEntity;
        QBookEntity qBook = QBookEntity.bookEntity;

        NumberExpression<Integer> totalAmount = qOrderBook.amount.sum();

        List<NumberedBookDTO> expectedResults = new ArrayList<>(); // Add your expected results
        expectedResults.add(BookMother.createNumberedBookDTO(3));
        expectedResults.add(BookMother.createNumberedBookDTO(2));

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(emMock);
        JPAQuery jpaQuery = mock(JPAQuery.class);
        when(jpaQueryFactory.selectFrom(any())).thenReturn(jpaQuery);
        when(jpaQuery.select(Projections.constructor(NumberedBookDTO.class, qBook, totalAmount))).thenReturn(jpaQuery);
        when(jpaQuery.join(qOrderBook.book, qBook)).thenReturn(jpaQuery);
        when(jpaQuery.groupBy(qBook.id)).thenReturn(jpaQuery);
        when(jpaQuery.orderBy(totalAmount.desc())).thenReturn(jpaQuery);
        when(jpaQuery.limit(limit)).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(expectedResults);

        // Act
        List<NumberedBookDTO> result = service.getTopProducts(limit);

        // Assert
        verify(jpaQueryFactory, times(1))
                .select(Projections.constructor(NumberedBookDTO.class, qBook, totalAmount))
                .from(qOrderBook)
                .join(qOrderBook.book, qBook)
                .groupBy(qBook.id)
                .orderBy(totalAmount.desc())
                .limit(limit)
                .fetch();

        assertEquals(expectedResults, result);
    }*/
}
