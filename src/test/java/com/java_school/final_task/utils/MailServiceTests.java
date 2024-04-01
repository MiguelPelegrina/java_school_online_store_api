package com.java_school.final_task.utils;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.utils.impl.MailServiceImpl;
import com.java_school.final_task.utils.impl.PDFServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import mothers.order.OrderMother;
import mothers.order_book.OrderBookMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MailServiceTests {
    @Mock
    private JavaMailSender mailSender;

    @Mock
    private SpringTemplateEngine templateEngine;

    @Mock
    private PDFServiceImpl pdfService;

    @InjectMocks
    private MailServiceImpl mailService;

    private MimeMessage message;
    private MimeMessageHelper helper;

    @BeforeEach
    public void setUp() {
        // Arrange
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.transport.protocol", "smtp");
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.smtp.starttls.enable", "true");
        mailProperties.setProperty("mail.debug", "false");

        message = new MimeMessage(Session.getDefaultInstance(mailProperties));
        try {
            helper = new MimeMessageHelper(message, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        when(mailSender.createMimeMessage()).thenReturn(message);
    }

    @Test
    public void MailService_SendOrderConfirmationMail() throws MessagingException {
        // Arrange
        SaveOrderDTO saveOrderDTO = SaveOrderDTO.builder()
                .order(OrderMother.createOrder())
                .orderedBooks(List.of(OrderBookMother.createOrderBook()))
                .build();

        helper.setSubject("Order confirmation at Online Bookstore");
        helper.setTo(saveOrderDTO.getOrder().getUser().getEmail());

        when(templateEngine.process(anyString(), any(Context.class))).thenReturn("Test HTML content");
        byte[] mockPdfBytes = "Mock PDF Content".getBytes();
        when(pdfService.generateOrderDetailsPDF(saveOrderDTO)).thenReturn(mockPdfBytes);

        // Act
        mailService.sendOrderConfirmationMail(saveOrderDTO);

        // Assert
        verify(mailSender).send(message);
        verify(templateEngine).process(eq("order-confirmation-mail"), any(Context.class));
        assertEquals("Order confirmation at Online Bookstore", message.getSubject());
    }

    // TODO Increase coverage
}
