package com.java_school.final_task.utils;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;

public interface MailService {
    void sendOrderConfirmationMail(SaveOrderDTO saveOrderDTO);

    //void sendRegistrationConfirmationMail(String to, String text);

    //void sendPasswordResetMail();
}
