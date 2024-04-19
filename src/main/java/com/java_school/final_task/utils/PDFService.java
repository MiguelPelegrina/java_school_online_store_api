package com.java_school.final_task.utils;

import com.java_school.final_task.domain.order.dto.SaveOrderDTO;

public interface PDFService {
    byte[] generateOrderDetailsPDF(SaveOrderDTO saveOrderDTO);
}
