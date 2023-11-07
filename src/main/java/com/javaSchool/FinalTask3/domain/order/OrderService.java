package com.javaSchool.FinalTask3.domain.order;

import com.javaSchool.FinalTask3.domain.order.deliveryMethod.DeliveryMethodEntity;
import com.javaSchool.FinalTask3.domain.order.dto.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.dto.SaveOrderDTO;
import com.javaSchool.FinalTask3.domain.order.impl.OrderRestControllerImpl;
import com.javaSchool.FinalTask3.domain.order.orderStatus.OrderStatusEntity;
import com.javaSchool.FinalTask3.domain.order.paymentMethod.PaymentMethodEntity;
import com.javaSchool.FinalTask3.domain.order.paymentStatus.PaymentStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

/**
 * Service interface responsible for the interaction between the {@link OrderRepository} and the
 * {@link OrderRestControllerImpl}. Obtains data from the {@link OrderRepository} and returns
 * the object(s) of the entity {@link OrderEntity} as {@link OrderDTO} to the {@link OrderRestControllerImpl}.
 */
public interface OrderService {
    /**
     * Retrieves a page of {@link OrderDTO}s from the database based on specified parameters and sorting criteria.
     * @param date            The date of the order.
     * @param deliveryMethod  The delivery method of the order to search for.
     * @param orderStatus     The order status of the order to search for.
     * @param paymentMethod   The payment method of the order to search for.
     * @param paymentStatus   The payment status of the order to search for.
     * @param name            The part of the name of the user to search for.
     * @return                ResponseEntity containing a Page of {@link OrderDTO}}s based on the specified criteria.
     */
    Page<OrderDTO> getAllInstances(LocalDate date, String deliveryMethod, String orderStatus,
                                   String paymentMethod, String paymentStatus, String name,
                                   PageRequest pageRequest);

    /**
     * Saves an order in the system based on the provided {@link SaveOrderDTO}.
     * @param saveOrderDTO A {@link SaveOrderDTO} containing order details to be saved.
     * @return An {@link OrderDTO} representing the saved order.
     */
    OrderDTO saveInstance(SaveOrderDTO saveOrderDTO);
}
