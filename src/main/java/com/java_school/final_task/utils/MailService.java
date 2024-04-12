package com.java_school.final_task.utils;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;

/**
 * Interface for sending emails related to orders.
 * Defines methods for sending order confirmation emails.
 */
public interface MailService {
    /**
     * Sends an order confirmation email based on the provided SaveOrderDTO.
     *
     * @param saveOrderDTO The SaveOrderDTO object containing the details of the order.
     */
    void sendOrderConfirmationMail(SaveOrderDTO saveOrderDTO);

    //void sendRegistrationConfirmationMail(String to, String text);

    //void sendPasswordResetMail();
}
