package com.javaSchool.FinalTask3.domain.order.impl;

import com.javaSchool.FinalTask3.domain.order.OrderRestController;
import com.javaSchool.FinalTask3.domain.order.dto.OrderDTO;
import com.javaSchool.FinalTask3.domain.order.dto.SaveOrderDTO;
import com.javaSchool.FinalTask3.domain.order.OrderEntity;
import com.javaSchool.FinalTask3.domain.order.OrderRepository;
import com.javaSchool.FinalTask3.utils.impl.AbstractRestControllerImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * RestController of the {@link OrderEntity} entity. Handles the REST methods. Uses {@link OrderDTO} as returning object.
 */
@RequestMapping(path = "orders")
@RestController
public class OrderRestControllerImpl
        extends AbstractRestControllerImpl<OrderServiceImpl, OrderRepository, OrderEntity, OrderDTO, Integer>
        implements OrderRestController {
    /**
     * All arguments constructor.
     * @param service {@link OrderServiceImpl} of the {@link OrderEntity} entity.
     */
    public OrderRestControllerImpl(OrderServiceImpl service) {
        super(service);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<Page<OrderDTO>> getAllInstances(
            @RequestParam(name = "date", defaultValue = "") LocalDate date,
            @RequestParam(name = "deliveryMethod", defaultValue = "") String deliveryMethod,
            @RequestParam(name = "orderStatus", defaultValue = "") String orderStatus,
            @RequestParam(name = "paymentMethod", defaultValue = "") String paymentMethod,
            @RequestParam(name = "paymentStatus", defaultValue = "") String paymentStatus,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam("sortType") String sortType,
            @RequestParam(name = "sortProperty", defaultValue = "surname") String sortProperty,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        // Check the sorting direction
        Sort.Direction direction = DESC.toString().equalsIgnoreCase(sortType) ? DESC : ASC;

        PageRequest pageRequest = PageRequest.of(page, size, direction, sortProperty);

        return ResponseEntity.ok(this.service.getAllInstances(
                date, deliveryMethod, orderStatus, paymentMethod, paymentStatus, name, pageRequest
        ));
    }

    // TODO This might not be the right approach, but I can't figure out how to create an JSON object with a circular
    //  reference on the frontend so that I can send a complete order.
    @PostMapping("/withBooks")
    @Operation(summary = "Saves an instance of an entity into the repository or updates it, if already exists.")
    @Override
    public ResponseEntity<OrderDTO> saveInstance(@RequestBody SaveOrderDTO saveOrderDTO){
        OrderDTO savedOrder = service.saveInstance(saveOrderDTO);

        if (savedOrder == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(savedOrder);
        }
    }
}
