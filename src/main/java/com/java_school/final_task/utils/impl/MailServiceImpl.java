package com.java_school.final_task.utils.impl;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import com.java_school.final_task.exception.MessagingExceptionWrapper;
import com.java_school.final_task.utils.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    private final PDFServiceImpl pdfService;

    @Value("${MAIL_USERNAME}")
    private String mailFromAddress;

    public MailServiceImpl(JavaMailSender mailSender, PDFServiceImpl pdfService, SpringTemplateEngine thymeleafTemplateEngine) {
        this.mailSender = mailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.pdfService = pdfService;
    }

    @Override
    public void sendOrderConfirmationMail(SaveOrderDTO saveOrderDTO) {
        try {
            byte[] pdfContent = pdfService.generateOrderDetailsPDF(saveOrderDTO);

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

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(saveOrderDTO.getOrder().getUser().getEmail());
            helper.setSubject("Order confirmation at Online Bookstore");

            // Set the content of the email
            helper.setText(htmlContent, true);

            // Add the PDF as an attachment
            String attachmentName = "order_" + saveOrderDTO.getOrder().getDate() + ".pdf";
            helper.addAttachment(attachmentName, new ByteArrayResource(pdfContent));

            this.sendMail(message);
        } catch (MessagingException e) {
            throw new MessagingExceptionWrapper();
        }

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
