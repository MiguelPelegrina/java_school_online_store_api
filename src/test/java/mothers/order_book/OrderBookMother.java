package mothers.order_book;

import com.java_school.final_task.domain.order_book.OrderBookDTO;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import mothers.book.BookMother;


public class OrderBookMother {
    public static OrderBookEntity createOrderBook() {
        return OrderBookEntity.builder()
                .id(1)
                .book(BookMother.createBook())
                .amount(1)
                .build();
    }

    public static OrderBookDTO createOrderBookDTO() {
        return OrderBookDTO.builder()
                .id(1)
                .book(BookMother.createBookDTO())
                .amount(1)
                .build();
    }
}
