package com.java_school.final_task.utils.impl;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import com.java_school.final_task.utils.PDFService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

/**
 * Service implementation for generating PDF documents.
 */
@Service
public class PDFServiceImpl implements PDFService {
    /**
     * Generates a PDF document containing order details based on the provided {@link SaveOrderDTO}.
     *
     * @param saveOrderDTO The {@code SaveOrderDTO} object containing the details of the order.
     * @return byte[] The byte array representing the generated PDF document.
     */
    @Override
    public byte[] generateOrderDetailsPDF(SaveOrderDTO saveOrderDTO) {
        // Set up variables
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();
        this.addOrderDetails(document, saveOrderDTO);
        document.close();

        return outputStream.toByteArray();
    }

    /**
     * Adds order details to the PDF document.
     *
     * @param document     The {@link Document} object representing the PDF document.
     * @param saveOrderDTO The {@link SaveOrderDTO} object containing the details of the order.
     */
    private void addOrderDetails(Document document, SaveOrderDTO saveOrderDTO) {
        addOrderConfirmationHeader(document);
        addOrderConfirmationBody(document, saveOrderDTO);
    }

    /**
     * Adds the header section to the PDF document.
     *
     * @param document The {@link Document} object representing the PDF document.
     */
    private void addOrderConfirmationHeader(Document document) {
        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontHeader.setSize(22);

        Paragraph headerParagraph = new Paragraph("Order Details", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(headerParagraph);
    }

    /**
     * Adds the body section with order details to the PDF document.
     *
     * @param document     The {@link Document} object representing the PDF document.
     * @param saveOrderDTO The {@link SaveOrderDTO} object containing the details of the order.
     */
    private void addOrderConfirmationBody(Document document, SaveOrderDTO saveOrderDTO) {
        Font fontParagraph = FontFactory.getFont(FontFactory.TIMES);
        fontParagraph.setSize(14);

        Paragraph pdfParagraph = new Paragraph("", fontParagraph);
        pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);

        pdfParagraph.add("Date: " + saveOrderDTO.getOrder().getDate());
        for (OrderBookEntity orderedBook : saveOrderDTO.getOrderedBooks()) {
            pdfParagraph.add(
                    "Title: " + orderedBook.getBook().getTitle() +
                            ", Price: " + orderedBook.getBook().getPrice() +
                            ", Amount: " + orderedBook.getAmount() +
                            ", Total: " + orderedBook.getTotal()
            );
        }
        pdfParagraph.add("Order total: " + saveOrderDTO.getOrderedBooks().stream().map(OrderBookEntity::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add));

        document.add(pdfParagraph);
    }
}
