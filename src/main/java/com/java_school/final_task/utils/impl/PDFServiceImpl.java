package com.java_school.final_task.utils.impl;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;
import com.java_school.final_task.domain.order_book.OrderBookEntity;
import com.java_school.final_task.utils.PDFService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

@Service
public class PDFServiceImpl implements PDFService {
    @Override
    public byte[] generateOrderDetailsPDF(SaveOrderDTO saveOrderDTO) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontHeader.setSize(22);

        Paragraph headerParagraph = new Paragraph("Order Details", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

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

        document.add(headerParagraph);
        document.add(pdfParagraph);
        document.close();
        return outputStream.toByteArray();
    }
}
