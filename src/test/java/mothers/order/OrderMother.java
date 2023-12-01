package mothers.order;

import com.java_school.final_task.domain.order.OrderEntity;
import com.java_school.final_task.domain.order.delivery_method.DeliveryMethodDTO;
import com.java_school.final_task.domain.order.delivery_method.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.dto.OrderDTO;
import com.java_school.final_task.domain.order.order_status.OrderStatusDTO;
import com.java_school.final_task.domain.order.order_status.OrderStatusEntity;
import com.java_school.final_task.domain.order.payment_method.PaymentMethodDTO;
import com.java_school.final_task.domain.order.payment_method.PaymentMethodEntity;
import com.java_school.final_task.domain.order.payment_status.PaymentStatusDTO;
import com.java_school.final_task.domain.order.payment_status.PaymentStatusEntity;
import com.java_school.final_task.domain.order_book.OrderBookJsonDTO;
import mothers.book.BookMother;
import mothers.order_book.OrderBookMother;
import mothers.user.UserMother;

import java.time.LocalDate;
import java.util.List;

public class OrderMother {
    public static OrderEntity createOrder() {
        return OrderEntity.builder()
                .id(0)
                .orderedBooks(null)
                .date(LocalDate.now())
                .deliveryMethod(DeliveryMethodEntity.builder().name("DeliveryMethod").isActive(true).build())
                .orderStatus(OrderStatusEntity.builder().name("OrderStatus").isActive(true).build())
                .paymentMethod(PaymentMethodEntity.builder().name("PaymentMethod").isActive(true).build())
                .paymentStatus(PaymentStatusEntity.builder().name("PaymentStatus").isActive(true).build())
                .orderedBooks(List.of(OrderBookMother.createOrderBook()))
                .user(UserMother.createUser())
                .build();
    }

    public static OrderDTO createOrderDTO() {
        return OrderDTO.builder()
                .id(0)
                .user(UserMother.createUserDTO())
                .date(LocalDate.now())
                .deliveryMethod(DeliveryMethodDTO.builder().name("DeliveryMethod").isActive(true).build())
                .orderStatus(OrderStatusDTO.builder().name("OrderStatus").isActive(true).build())
                .paymentMethod(PaymentMethodDTO.builder().name("PaymentMethod").isActive(true).build())
                .paymentStatus(PaymentStatusDTO.builder().name("PaymentStatus").isActive(true).build())
                .orderedBooks(List.of(createOrderBookJsonDTO()))
                .build();
    }

    public static OrderBookJsonDTO createOrderBookJsonDTO() {
        return OrderBookJsonDTO.builder()
                .id(1)
                .book(BookMother.createBookDTO())
                .amount(1)
                .build();
    }
}
