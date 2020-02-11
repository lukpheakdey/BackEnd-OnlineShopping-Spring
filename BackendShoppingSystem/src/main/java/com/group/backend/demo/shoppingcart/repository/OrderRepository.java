package com.group.backend.demo.shoppingcart.repository;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.shoppingcart.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long > {

    List<Order> findByUserID(long userId);

}
