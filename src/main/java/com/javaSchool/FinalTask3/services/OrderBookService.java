package com.javaSchool.FinalTask3.services;

import com.javaSchool.FinalTask3.dtos.OrderBookDTO;
import com.javaSchool.FinalTask3.entities.OrderBook;
import com.javaSchool.FinalTask3.entities.embeddables.OrderBookId;
import com.javaSchool.FinalTask3.repositories.OrderBookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderBookService {
    private final OrderBookRepository repository;

    private final ModelMapper modelMapper;

    public List<OrderBookDTO> getAllOrderBooks(){
        return repository.findAll().stream()
                .map((orderBook) ->
                        modelMapper.map(orderBook, OrderBookDTO.class))
                .collect(Collectors.toList());
    }

    public OrderBookDTO getOrderBookById(OrderBookId id){
        return modelMapper.map(repository.findById(id)
                .orElse(null), OrderBookDTO.class);
    }

    @Transactional
    public OrderBookDTO saveOrderBook(OrderBook orderBook){
        return modelMapper.map(repository.save(orderBook), OrderBookDTO.class);
    }

    @Transactional
    public OrderBookDTO updateOrderBook(OrderBookId id, OrderBook orderBook){
        return repository.findById(id)
                .map(existingOrderBook -> {
                    existingOrderBook.setOrder(orderBook.getOrder());
                    existingOrderBook.setBook(orderBook.getBook());
                    existingOrderBook.setAmount(orderBook.getAmount());
                    return modelMapper.map(repository.save(existingOrderBook), OrderBookDTO.class);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteOrderBook(OrderBookId id){
        repository.deleteById(id);
    }
}
