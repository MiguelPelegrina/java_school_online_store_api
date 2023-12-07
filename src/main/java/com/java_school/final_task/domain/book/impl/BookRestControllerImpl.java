package com.java_school.final_task.domain.book.impl;

import com.java_school.final_task.domain.book.BookEntity;
import com.java_school.final_task.domain.book.BookRepository;
import com.java_school.final_task.domain.book.BookRestController;
import com.java_school.final_task.domain.book.dto.BookDTO;
import com.java_school.final_task.domain.book.dto.BookRequestDTO;
import com.java_school.final_task.domain.book.dto.NumberedBookDTO;
import com.java_school.final_task.utils.impl.AbstractRestControllerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController of the {@link BookEntity} entity. Handles the REST methods. Uses {@link BookDTO} as returning object.
 */
@RequestMapping(path = "books")
@RestController
public class BookRestControllerImpl
        extends AbstractRestControllerImpl<BookServiceImpl, BookRepository, BookEntity, BookDTO, Integer>
        implements BookRestController {
    /**
     * All arguments constructor.
     *
     * @param service {@link BookServiceImpl} of the {@link BookEntity} entity.
     */
    public BookRestControllerImpl(BookServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved list of books",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @Operation(summary = "Saves a list of books")
    @Override
    @PostMapping("/save_all")
    public ResponseEntity<List<BookDTO>> saveInstances(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "List of books", required = true,
                    content = @Content(schema = @Schema(implementation = BookEntity.class)))
            @RequestBody List<BookEntity> instances) {
        List<BookDTO> instanceDTOs = service.saveInstances(instances);

        if (instanceDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(instanceDTOs, HttpStatus.CREATED);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved books",
                    content = {@Content(mediaType = "application/json", schema = @Schema)})
    })
    @GetMapping("/search")
    @Operation(summary = "Retrieves a list of filtered books")
    @Override
    public ResponseEntity<Page<BookDTO>> getAllInstances(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Book request", required = true,
                    content = @Content(schema = @Schema(implementation = BookRequestDTO.class)))
            BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(this.service.getAllInstances(bookRequestDTO));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved the books",
                    content = {@Content(mediaType = "application/json", schema = @Schema)}),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/top_products")
    @Operation(summary = "Retrieves the most sold products")
    @Override
    public ResponseEntity<List<NumberedBookDTO>> getTopProducts(
            @Parameter(description = "id of the instance to be searched", example = "5")
            @RequestParam("limit") int limit) {
        return ResponseEntity.ok(this.service.getTopProducts(limit));
    }
}
