package com.java_school.final_task.utils;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;

/**
 * Interface for PDF generation service.
 * Defines methods for generating PDF documents related to orders.
 */
public interface PDFService {
    /**
     * Generates a PDF document containing order details based on the provided SaveOrderDTO.
     *
     * @param saveOrderDTO The SaveOrderDTO object containing the details of the order.
     * @return byte[] The byte array representing the generated PDF document.
     */
    byte[] generateOrderDetailsPDF(SaveOrderDTO saveOrderDTO);
}
