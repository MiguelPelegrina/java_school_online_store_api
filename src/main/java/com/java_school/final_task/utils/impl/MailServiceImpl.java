package com.java_school.final_task.utils.impl;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import com.java_school.final_task.exception.MessagingExceptionWrapper;
import com.java_school.final_task.utils.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    private final SpringTemplateEngine thymeleafTemplateEngine;

    @Value("${MAIL_USERNAME}")
    private String mailFromAddress;

    public MailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine thymeleafTemplateEngine) {
        this.mailSender = mailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    @Override
    public void sendOrderConfirmationMail(SaveOrderDTO saveOrderDTO) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setRecipients(MimeMessage.RecipientType.TO, saveOrderDTO.getOrder().getUser().getEmail());
            message.setSubject("Order confirmation at Online Bookstore");

            // Prepare the model for the template
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("customerName", saveOrderDTO.getOrder().getUser().getName());
            templateModel.put("orderedBooks", saveOrderDTO.getOrderedBooks());
            BigDecimal total = saveOrderDTO.getOrderedBooks().stream().map(OrderBookEntity::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
            templateModel.put("total", total);

            // Process the template
            Context thymeleafContext = new Context();
            thymeleafContext.setVariables(templateModel);
            String htmlContent = thymeleafTemplateEngine.process("order-confirmation-mail", thymeleafContext);

            // Set the content of the email
            message.setContent(htmlContent, "text/html; charset=utf-8");
        } catch (MessagingException e) {
            throw new MessagingExceptionWrapper();
        }

        this.sendMail(message);
    }

    /*@Override
    public void sendRegistrationConfirmationMail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(text);

        message.setSubject("User registration at Online Bookstore");

        this.sendMail(message);
    }*/

    private void sendMail(MimeMessage message) {
        try {
            message.setFrom(mailFromAddress);
        } catch (MessagingException e) {
            throw new MessagingExceptionWrapper();
        }

        mailSender.send(message);
    }
}
