package com.java_school.final_task.domain.order;

import com.java_school.final_task.domain.orderBook.OrderBookEntity;
import com.java_school.final_task.domain.user.UserEntity;
import com.java_school.final_task.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.java_school.final_task.domain.order.orderStatus.OrderStatusEntity;
import com.java_school.final_task.domain.order.paymentMethod.PaymentMethodEntity;
import com.java_school.final_task.domain.order.paymentStatus.PaymentStatusEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
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

    //{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderBookEntity> orderedBooks;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}
