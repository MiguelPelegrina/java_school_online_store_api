package com.java_school.final_task.domain.order_book;

import com.java_school.final_task.domain.book.dto.BookDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) of {@link OrderBookEntity}.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "Data Transfer Object of a ordered book")
public class OrderBookDTO {
    private int id;
    private BookDTO book;
    private int amount;
}
