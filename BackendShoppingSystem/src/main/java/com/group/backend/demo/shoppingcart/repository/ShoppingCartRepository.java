package com.group.backend.demo.shoppingcart.repository;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.shoppingcart.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUser(User user);
}
