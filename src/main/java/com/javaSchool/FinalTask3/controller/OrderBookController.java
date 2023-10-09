package com.javaSchool.FinalTask3.controller;

import com.javaSchool.FinalTask3.dtos.OrderBookDTO;
import com.javaSchool.FinalTask3.entities.OrderBook;
import com.javaSchool.FinalTask3.entities.embeddables.OrderBookId;
import com.javaSchool.FinalTask3.services.OrderBookService;
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
        return new ResponseEntity<>(service.getAllOrderBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderBookDTO> getOrderBookById(@PathVariable OrderBookId id){
        return new ResponseEntity<>(service.getOrderBookById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderBookDTO> saveOrderBook(@RequestBody OrderBook orderBook){
        OrderBookDTO savedOrderBook = service.saveOrderBook(orderBook);

        if(savedOrderBook == null){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedOrderBook, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderBookDTO> updateOrderBook(@PathVariable OrderBookId id, @RequestBody OrderBook orderBook){
        return new ResponseEntity<>(service.updateOrderBook(id, orderBook), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderBook(@PathVariable OrderBookId id){
        service.deleteOrderBook(id);
    }
}
