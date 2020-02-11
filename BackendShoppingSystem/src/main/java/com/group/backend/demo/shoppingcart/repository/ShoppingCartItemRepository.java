package com.group.backend.demo.shoppingcart.repository;

import com.group.backend.demo.shoppingcart.domain.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

}
