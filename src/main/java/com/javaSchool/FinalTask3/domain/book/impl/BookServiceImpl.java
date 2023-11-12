package com.javaSchool.FinalTask3.domain.book.impl;

import com.javaSchool.FinalTask3.domain.book.*;
import com.javaSchool.FinalTask3.domain.book.dto.BookDTO;
import com.javaSchool.FinalTask3.domain.book.dto.NumberedBookDTO;
import com.javaSchool.FinalTask3.domain.orderBook.QOrderBookEntity;
import com.javaSchool.FinalTask3.utils.impl.AbstractServiceImpl;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class responsible for the interaction between the {@link BookRepository} and the
 * {@link BookRestControllerImpl}. Obtains data from the {@link BookRepository} and returns
 * the object(s) of the entity {@link BookEntity} as {@link BookDTO} to the {@link BookRestControllerImpl}.
 */
@Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
@Service
@Transactional(readOnly = true)
public class BookServiceImpl
        extends AbstractServiceImpl<BookRepository, BookEntity, BookDTO, Integer>
        implements BookService {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * All arguments constructor.
     * @param repository {@link BookRepository} of the {@link BookEntity} entity.
     * @param modelMapper ModelMapper that converts the {@link BookEntity} to {@link BookDTO}
     */
    public BookServiceImpl(BookRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
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

    @Secured({"ROLE_ADMIN","ROLE_EMPLOYEE"})
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
}
