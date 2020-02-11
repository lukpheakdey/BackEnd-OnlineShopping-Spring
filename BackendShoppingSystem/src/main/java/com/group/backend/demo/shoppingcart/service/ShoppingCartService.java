package com.group.backend.demo.shoppingcart.service;


import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.payment.model.PaymentForm;
import com.group.backend.demo.shoppingcart.domain.OrderConfirmation;
import com.group.backend.demo.shoppingcart.domain.ShoppingCart;
import com.group.backend.demo.shoppingcart.exception.PlaceOrderException;
import com.group.backend.demo.shoppingcart.exception.ProductNotEnoughException;
import com.group.backend.demo.shoppingcart.exception.ProductNotFoundException;
import com.group.backend.demo.shoppingcart.exception.ShoppingCartEmptyException;
import com.group.backend.demo.vendor.domain.Product;

public interface ShoppingCartService {





    ShoppingCart findCart(User user);

    ShoppingCart createShoppingCart(User user);

    void addProductToCart(ShoppingCart cart, Product product);

    void removeProductFromCart(ShoppingCart cart, Product product) throws ProductNotFoundException;

    void updateCartItem(ShoppingCart cart, Product product, int quantity) throws ProductNotEnoughException, ProductNotFoundException;

    OrderConfirmation proceedCart(ShoppingCart cart, PaymentForm form) throws PlaceOrderException;

    OrderConfirmation convertItemsForConfirm(ShoppingCart cart) throws ShoppingCartEmptyException;

}
