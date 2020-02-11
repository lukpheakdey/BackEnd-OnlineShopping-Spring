package com.group.backend.demo.shoppingcart.service;

import com.group.backend.demo.shoppingcart.domain.Order;

import java.io.FileNotFoundException;
import java.util.List;

public interface OrderService {

    void addOrder(Order order);

    List<Order> getOrdersByUserId(Long orderId) throws FileNotFoundException;

}
