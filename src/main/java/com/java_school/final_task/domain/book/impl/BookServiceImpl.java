package com.java_school.final_task.domain.book.impl;

import com.java_school.final_task.domain.book.*;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import com.java_school.final_task.domain.book.genre.BookGenreRepository;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterRepository;
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

    private final JPAQueryFactory queryFactory;

    /**
     * All arguments constructor.
     *
     * @param repository              {@link BookRepository} of the {@link BookEntity} entity.
     * @param modelMapper             ModelMapper that converts the {@link BookEntity} to {@link BookDTO}
     * @param bookParameterRepository {@link BookParameterRepository} of the {@link BookParameterEntity}
     * @param queryFactory            {@link QueryFactory} to create queries.
     */
    public BookServiceImpl(BookRepository repository,
                           ModelMapper modelMapper,
                           BookParameterRepository bookParameterRepository,
                           BookGenreRepository bookGenreRepository,
                           JPAQueryFactory queryFactory) {
        super(repository, modelMapper);
        this.bookParameterRepository = bookParameterRepository;
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
    public Page<BookDTO> getAllInstances(BookRequest bookRequest) {
        // Variables
        final QBookEntity qBook = QBookEntity.bookEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        bookRequest.getActive().ifPresent(aBoolean -> queryBuilder.and(qBook.active.eq(aBoolean)));

        if (!bookRequest.getName().isEmpty()) {
            queryBuilder.and(qBook.title.containsIgnoreCase(bookRequest.getName())
                    .or(qBook.parameters.author.containsIgnoreCase(bookRequest.getName())));
        }

        if (!bookRequest.getGenre().isEmpty()) {
            queryBuilder.and(qBook.genre.name.containsIgnoreCase(bookRequest.getGenre()));
        }

        PageRequest pageRequest = PageRequest.of(
                bookRequest.getPage(),
                bookRequest.getSize(),
                Sort.Direction.valueOf(bookRequest.getSortType()),
                bookRequest.getSortProperty());

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
     * Overrides the base saveInstance method to handle BookEntity instances.
     * Before saving a BookEntity, checks if there are existing BookParameterEntity instances
     * with the same author and format. If found, associates the first matching BookParameterEntity
     * with the BookEntity's parameters. Saves the BookEntity instance and returns a BookDTO.
     *
     * @param book {@link BookEntity} instance to be saved.
     * @return {@link BookDTO} representing the saved BookEntity.
     */
    @Override
    public BookDTO saveInstance(BookEntity book) {
        List<BookParameterEntity> bookParameters = bookParameterRepository.findByAuthorAndFormat(
                book.getParameters().getAuthor(),
                book.getParameters().getFormat()
        );

        if (!bookParameters.isEmpty()) {
            book.setParameters(bookParameters.get(0));
        }

        return super.saveInstance(book);
    }

    @Override
    @Transactional
    public List<BookDTO> saveInstances(List<BookEntity> books) {
        // I assume this could be done better and more efficiently with
        // - saveAll most likely, being much more efficient, but that would require to restructure the bookParameters
        // to have a composite primary key(?)
        List<BookDTO> bookDTOs = new ArrayList<>();

        books.forEach(book ->
                {
                    bookDTOs.add(modelMapper.map(this.saveInstance(book), getDTOClass()));
                }
        );

        return bookDTOs;
    }
}
