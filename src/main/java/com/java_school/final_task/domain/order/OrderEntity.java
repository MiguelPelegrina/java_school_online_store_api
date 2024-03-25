package com.java_school.final_task.domain.order;

import com.java_school.final_task.domain.order.delivery_method.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.order_status.OrderStatusEntity;
import com.java_school.final_task.domain.order.payment_method.PaymentMethodEntity;
import com.java_school.final_task.domain.order.payment_status.PaymentStatusEntity;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import com.java_school.final_task.domain.user.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Schema(description = "A order of a user to buy several books")
@Table(name = "orders", schema = "public", catalog = "online_store")
public class OrderEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_method", referencedColumnName = "name", nullable = false)
    private DeliveryMethodEntity deliveryMethod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_status", referencedColumnName = "name", nullable = false)
    private OrderStatusEntity orderStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method", referencedColumnName = "name", nullable = false)
    private PaymentMethodEntity paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_status", referencedColumnName = "name", nullable = false)
    private PaymentStatusEntity paymentStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderBookEntity> orderedBooks;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public BigDecimal getTotal() {
        return this.orderedBooks.stream()
                .map(OrderBookEntity::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
