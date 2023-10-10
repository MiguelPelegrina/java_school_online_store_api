package com.javaSchool.FinalTask3.domain.orderBook;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("orderbooks")
@RequiredArgsConstructor
@RestController
public class OrderBookController {
    private final OrderBookService service;

    @GetMapping
    public ResponseEntity<List<OrderBookDTO>> getAllOrderBooks(){
        return new ResponseEntity<>(service.getAllInstances(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderBookDTO> getOrderBookById(@PathVariable OrderBookId id){
        return new ResponseEntity<>(service.getInstanceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderBookDTO> saveOrderBook(@RequestBody OrderBookEntity orderBook){
        OrderBookDTO savedOrderBook = service.saveInstance(orderBook);

        if(savedOrderBook == null){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedOrderBook, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderBookDTO> updateOrderBook(@PathVariable OrderBookId id, @RequestBody OrderBookEntity orderBook){
        return new ResponseEntity<>(service.updateInstance(id, orderBook), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderBook(@PathVariable OrderBookId id){
        service.deleteInstance(id);
    }
}
