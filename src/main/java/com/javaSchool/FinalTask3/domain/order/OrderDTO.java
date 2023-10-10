package com.javaSchool.FinalTask3.domain.order;

import com.javaSchool.FinalTask3.domain.user.UserDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {
    private int id;
    private UserDTO user;
    private String deliveryMethod;
    private String orderStatus;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDate date;
}
