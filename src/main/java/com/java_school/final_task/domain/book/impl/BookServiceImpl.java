package com.java_school.final_task.domain.book.impl;

import com.javaSchool.finalTask.domain.book.*;
import com.java_school.final_task.domain.book.*;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import com.java_school.final_task.domain.book.parameter.BookParameterEntity;
import com.java_school.final_task.domain.book.parameter.BookParameterRepository;
import com.java_school.final_task.domain.orderBook.QOrderBookEntity;
import com.java_school.final_task.utils.impl.AbstractServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestControllerImpl}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
@Service
public class BookServiceImpl
        extends AbstractServiceImpl<BookRepository, BookEntity, BookDTO, Integer>
        implements BookService {
    // Fields
    private final BookParameterRepository bookParameterRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * All arguments constructor.
     *
     * @param repository              {@link BookRepository} of the {@link BookEntity} entity.
     * @param modelMapper             ModelMapper that converts the {@link BookEntity} to {@link BookDTO}
     * @param bookParameterRepository {@link BookParameterRepository} of the {@link BookParameterEntity}
     */
    public BookServiceImpl(BookRepository repository, ModelMapper modelMapper, BookParameterRepository bookParameterRepository) {
        super(repository, modelMapper);
        this.bookParameterRepository = bookParameterRepository;
    }

    @Override
    public Class<BookDTO> getDTOClass() {
        return BookDTO.class;
    }

    @Override
    public Integer getEntityId(BookEntity instance) {
        return instance.getId();
    }

    // TODO
    //  Scalable by
    //  - Differentiating between filtering with AND (requires to be advanced filter in FE) and OR (searching by title,
    //  or author, or ISBN) --> just use another RequestParam Optional<Boolean> advanced or a different mapping?
    //  - Adding an Array of String for sorting
    //  - Adding an Array of Genres for filtering
    @Override
    public Page<BookDTO> getAllInstances(BookRequest bookRequest) {
        // Variables
        final QBookEntity qBook = QBookEntity.bookEntity;
        final BooleanBuilder queryBuilder = new BooleanBuilder();

        // Check which parameters are present and build a query
        bookRequest.getActive().ifPresent(aBoolean -> queryBuilder.and(qBook.active.eq(aBoolean)));

        if(!bookRequest.getName().isEmpty()){
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
    public List<NumberedBookDTO> getTopProducts(int limit
                                                //, int page, int size
    ){
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);

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
     * @param book {@link BookEntity} instance to be saved.
     * @return {@link BookDTO} representing the saved BookEntity.
     */
    @Override
    public BookDTO saveInstance(BookEntity book){
        List<BookParameterEntity> bookParameters = bookParameterRepository.findByAuthorAndFormat(
                book.getParameters().getAuthor(),
                book.getParameters().getFormat()
        );

        if(!bookParameters.isEmpty()){
           book.setParameters(bookParameters.get(0));
        }

        return super.saveInstance(book);
    }
}
