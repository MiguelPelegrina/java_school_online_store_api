package com.java_school.final_task.utils.impl;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import com.java_school.final_task.exception.MessagingExceptionWrapper;
import com.java_school.final_task.utils.MailService;
import com.java_school.final_task.utils.PDFService;
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

/**
 * {@link MailService} implementation responsible for sending emails.
 * This class implements the MailService interface and provides functionality to generate HTML content,
 * attach PDF documents, and send emails using Spring's JavaMailSender.
 */
@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final PDFService pdfService;
    private final String mailFromAddress;

    /**
     * Constructs a new MailServiceImpl with the specified dependencies.
     *
     * @param mailSender              The {@link JavaMailSender} instance used to send emails.
     * @param pdfService              The {@link PDFServiceImpl} instance used to generate PDF documents.
     * @param thymeleafTemplateEngine The {@link SpringTemplateEngine} instance used to process HTML templates.
     * @param mailFromAddress         The email address from which the emails will be sent.
     */
    public MailServiceImpl(
            JavaMailSender mailSender,
            PDFService pdfService,
            SpringTemplateEngine thymeleafTemplateEngine,
            @Value("${MAIL_USERNAME}") String mailFromAddress
    ) {
        this.mailSender = mailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.pdfService = pdfService;
        this.mailFromAddress = mailFromAddress;
    }

    /**
     * Sends an order confirmation email to the user with the order details.
     *
     * @param saveOrderDTO The {@link SaveOrderDTO} instance containing the order details.
     */
    @Override
    public void sendOrderConfirmationMail(SaveOrderDTO saveOrderDTO) {
        byte[] pdfContent = pdfService.generateOrderDetailsPDF(saveOrderDTO);

        String htmlContent = this.generateHTMLContent(saveOrderDTO);

        MimeMessage message = createMimeMessage(saveOrderDTO, htmlContent, pdfContent);

        mailSender.send(message);
    }

    /**
     * Generates HTML content for the order confirmation email.
     *
     * @param saveOrderDTO The {@code SaveOrderDTO} containing the order details.
     * @return The HTML content of the email.
     */
    private String generateHTMLContent(SaveOrderDTO saveOrderDTO) {
        Map<String, Object> templateModel = this.generateTemplateModel(saveOrderDTO);
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        return thymeleafTemplateEngine.process("order-confirmation-mail", thymeleafContext);
    }

    /**
     * Generates a template model for the order confirmation email.
     *
     * @param saveOrderDTO The {@code SaveOrderDTO} instance containing the order details.
     * @return The template model containing data for the email template.
     */
    private Map<String, Object> generateTemplateModel(SaveOrderDTO saveOrderDTO) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("customerName", saveOrderDTO.getOrder().getUser().getName());
        templateModel.put("orderedBooks", saveOrderDTO.getOrderedBooks());
        BigDecimal total = saveOrderDTO.getOrderedBooks().stream().map(OrderBookEntity::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        templateModel.put("total", total);
        return templateModel;
    }

    /**
     * Creates a MimeMessage for the order confirmation email.
     *
     * @param saveOrderDTO The {@code SaveOrderDTO} instance containing the order details.
     * @param htmlContent  The HTML content of the email.
     * @param pdfContent   The content of the PDF attachment.
     * @return The MimeMessage for the email.
     */
    private MimeMessage createMimeMessage(SaveOrderDTO saveOrderDTO, String htmlContent, byte[] pdfContent) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(saveOrderDTO.getOrder().getUser().getEmail());
            helper.setSubject("Order confirmation at Online Bookstore");
            helper.setText(htmlContent, true);

            String attachmentName = "order_" + saveOrderDTO.getOrder().getDate() + ".pdf";
            helper.addAttachment(attachmentName, new ByteArrayResource(pdfContent));
            message.setFrom(mailFromAddress);
        } catch (MessagingException e) {
            throw new MessagingExceptionWrapper();
        }

        return message;
    }

    /*@Override
    public void sendRegistrationConfirmationMail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(text);

        message.setSubject("User registration at Online Bookstore");

        this.sendMail(message);
    }*/
}
