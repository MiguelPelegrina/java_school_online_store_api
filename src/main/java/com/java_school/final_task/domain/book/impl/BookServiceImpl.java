package com.java_school.final_task.domain.book.impl;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.domain.book.BookService;
import com.java_school.final_task.domain.book.QBookEntity;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.BookRequestDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import com.java_school.final_task.domain.book.genre.BookGenreEntity;
import com.java_school.final_task.domain.book.genre.BookGenreRepository;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterRepository;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatEntity;
import com.java_school.final_task.domain.book.parameter.format.BookParametersFormatRepository;
import com.java_school.final_task.domain.order_book.QOrderBookEntity;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestControllerImpl}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
@Service
public class BookServiceImpl
        extends AbstractServiceImpl<BookRepository, BookEntity, BookDTO, Integer>
        implements BookService {
    // Fields
    private final BookParameterRepository bookParameterRepository;

    private final BookGenreRepository bookGenreRepository;

    private final BookParametersFormatRepository bookParametersFormatRepository;

    private final JPAQueryFactory queryFactory;

    /**
     * All arguments constructor.
     *
     * @param repository                     {@link BookRepository} of the {@link BookEntity} entity.
     * @param modelMapper                    ModelMapper that converts the {@link BookEntity} to {@link BookDTO}
     * @param bookParameterRepository        {@link BookParameterRepository} of the {@link BookParameterEntity}
     * @param bookGenreRepository            {@link BookGenreRepository} of the {@link BookGenreEntity}
     * @param bookParametersFormatRepository {@link BookParametersFormatRepository} of the {@link BookParametersFormatEntity}
     * @param queryFactory                   {@link QueryFactory} to create queries.
     */
    public BookServiceImpl(BookRepository repository,
                           ModelMapper modelMapper,
                           BookParameterRepository bookParameterRepository,
                           BookGenreRepository bookGenreRepository,
                           BookParametersFormatRepository bookParametersFormatRepository,
                           JPAQueryFactory queryFactory) {
        super(repository, modelMapper);
        this.bookParameterRepository = bookParameterRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.bookParametersFormatRepository = bookParametersFormatRepository;
        this.queryFactory = queryFactory;
    }

    @Override
    public Class<BookDTO> getDTOClass() {
        return BookDTO.class;
    }

    @Override
    public Integer getEntityId(BookEntity instance) {
        return instance.getId();
    }

    @Override
    public Page<BookDTO> getAllInstances(BookRequestDTO bookRequestDTO) {
        // Variables
        final QBookEntity qBook = QBookEntity.bookEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        bookRequestDTO.getActive().ifPresent(aBoolean -> queryBuilder.and(qBook.active.eq(aBoolean)));

        if (!bookRequestDTO.getName().isEmpty()) {
            queryBuilder.and(qBook.title.containsIgnoreCase(bookRequestDTO.getName())
                    .or(qBook.parameters.author.containsIgnoreCase(bookRequestDTO.getName())));
        }

        if (!bookRequestDTO.getGenre().isEmpty()) {
            queryBuilder.and(qBook.genre.name.containsIgnoreCase(bookRequestDTO.getGenre()));
        }

        PageRequest pageRequest = PageRequest.of(
                bookRequestDTO.getPage(),
                bookRequestDTO.getSize(),
                Sort.Direction.valueOf(bookRequestDTO.getSortType()),
                bookRequestDTO.getSortProperty());

        // Find the data in the repository
        Page<BookEntity> pageEntities = this.repository.findAll(queryBuilder, pageRequest);

        // Convert the book page to a bookDTO page
        return pageEntities.map(book ->
                modelMapper.map(book, this.getDTOClass())
        );
    }

    @Override
    public List<NumberedBookDTO> getTopProducts(int limit) {
        QOrderBookEntity qOrderBook = QOrderBookEntity.orderBookEntity;
        QBookEntity qBook = QBookEntity.bookEntity;

        NumberExpression<Integer> totalAmount = qOrderBook.amount.sum();

        return queryFactory
                .select(Projections.constructor(NumberedBookDTO.class, qBook, totalAmount))
                .from(qOrderBook)
                .join(qOrderBook.book, qBook)
                .groupBy(qBook.id)
                .orderBy(totalAmount.desc())
                .limit(limit)
                .fetch();
    }

    /**
     * Saves the BookEntity instance and returns a BookDTO.
     *
     * @param book {@link BookEntity} instance to be saved.
     * @return {@link BookDTO} representing the saved BookEntity.
     */
    @Override
    public BookDTO saveInstance(BookEntity book) {
        setBookParametersByAuthorAndFormat(book);

        return super.saveInstance(book);
    }

    @Override
    @Transactional
    public List<BookDTO> saveInstances(List<BookEntity> books) {
        List<BookDTO> bookDTOs = new ArrayList<>();

        books.forEach(book -> {
            setBookGenre(book);
            setBookParametersFormat(book);
            setBookParametersByAuthorAndFormat(book);
            bookDTOs.add(modelMapper.map(super.saveInstance(book), getDTOClass()));
        });

        return bookDTOs;
    }

    /**
     * Sets the {@link BookGenreEntity} of the given book based on the genre's name.
     *
     * @param book The {@link BookEntity} for which to set the genre.
     */
    private void setBookGenre(BookEntity book) {
        Optional<BookGenreEntity> bookGenre = bookGenreRepository.findById(
                String.valueOf(book.getGenre().getName())
        );

        bookGenre.ifPresent(book::setGenre);
    }

    /**
     * Sets the {@link BookParametersFormatEntity} of the given book's parameters based on the format's name.
     *
     * @param book The {@link BookEntity} for which to set the parameters' format.
     */
    private void setBookParametersFormat(BookEntity book) {
        Optional<BookParametersFormatEntity> bookParametersFormat = bookParametersFormatRepository.findById(
                String.valueOf(book.getParameters().getFormat().getName())
        );

        bookParametersFormat.ifPresent(bookParametersFormatEntity ->
                book.getParameters().setFormat(bookParametersFormatEntity)
        );
    }

    /**
     * Sets the {@link BookParameterEntity} of the given book based on the author and format.
     *
     * @param book The {@link BookEntity} for which to set the parameters.
     */
    private void setBookParametersByAuthorAndFormat(BookEntity book) {
        List<BookParameterEntity> bookParameters = bookParameterRepository.findByAuthorAndFormat(
                book.getParameters().getAuthor(),
                book.getParameters().getFormat()
        );

        if (!bookParameters.isEmpty()) {
            book.setParameters(bookParameters.get(0));
        }
    }
}
