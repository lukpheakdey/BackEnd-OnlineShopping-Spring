package com.group.backend.demo.shoppingcart.service.impl;

import com.group.backend.demo.authentication.repository.UserRepository;
import com.group.backend.demo.authentication.service.UserServiceImplementation;
import com.group.backend.demo.shoppingcart.domain.Order;
import com.group.backend.demo.shoppingcart.repository.OrderRepository;
import com.group.backend.demo.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;



    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long orderId) throws FileNotFoundException {

        List<Order> orders = new ArrayList<>();
        if(orders == null || orders.size() == 0) throw new FileNotFoundException("Order not found");
        return orderRepository.findByUserID(orderId);
    }
}