package com.group.backend.demo.shoppingcart.controller;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.authentication.service.UserServiceImplementation;
import com.group.backend.demo.payment.model.PaymentForm;
import com.group.backend.demo.shoppingcart.domain.*;
import com.group.backend.demo.shoppingcart.exception.*;
import com.group.backend.demo.shoppingcart.service.OrderService;
import com.group.backend.demo.shoppingcart.service.ShoppingCartService;
import com.group.backend.demo.vendor.domain.Product;
import com.group.backend.demo.vendor.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserServiceImplementation userService;

    @PostMapping(value = "/add_item")
    public ResponseEntity<Void> addProductToCart(@RequestBody  CartRequest request, HttpSession session, Principal principal) throws ProductNotFoundException {

        ShoppingCart cart = getShoppingCart(principal, session);

        Product product = productService.findById(request.getProductId());
        if(product == null)
            throw new ProductNotFoundException();

        shoppingCartService.addProductToCart(cart, product);
        System.out.println(cart);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @PostMapping(value = "/delete_cartitem")
    public ResponseEntity<Void> removeProduct(@RequestBody Long productId, Principal principal, HttpSession session) throws ProductNotFoundException {

       ShoppingCart cart = getShoppingCart(principal,session);

        Product product = productService.findById(productId);
        if(product == null)
            throw new ProductNotFoundException();

        shoppingCartService.removeProductFromCart(cart, product);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private ShoppingCart getShoppingCart(Principal principal, HttpSession session){

        ShoppingCart cart = null;
        if(session.getAttribute("cart" ) == null){
            User user = userService.loadUserByUsername(principal.getName());
            //not in the session.
            cart = shoppingCartService.findCart(user);
            if(cart == null)
                cart = shoppingCartService.createShoppingCart(user);
            session.setAttribute("cart", cart);
        }
        return (ShoppingCart) session.getAttribute("cart");

    }



    @PostMapping(value = "/updatecartqty")
    public ResponseEntity<Void> updateProduct( @RequestBody UpdateRequest request,
            Principal principal, HttpSession session)
            throws CredentialException, ProductNotFoundException, ProductNotEnoughException {

        ShoppingCart cart = getShoppingCart(principal, session);

        Product product = productService.findById(request.getProductId());
        if(product == null)
            throw new ProductNotFoundException();
        shoppingCartService.updateCartItem(cart, product, request.getQty());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItemResponse>> getShoppingCartItems(HttpSession ses, Principal principal) {
        ShoppingCart cart = getShoppingCart(principal, ses);
        return new ResponseEntity(getCartItems(cart.getShoppingCartItemList()), HttpStatus.OK);
    }



    private List<CartItemResponse> getCartItems(List<ShoppingCartItem> res){
        List<CartItemResponse> responses = new ArrayList<>();
        for(ShoppingCartItem cartItem : res){
            Product product = cartItem.getProduct();
            CartItemResponse resp = new CartItemResponse((int)product.getProductId(), product.getProductName(),
                    cartItem.getQuantity(), product.getDescription()
            ,product.getPrice(), (product.getPrice() * cartItem.getQuantity()), product.getProductImage());
            responses.add(resp);
        }
        return responses;
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderConfirmation> proceed(Principal principal, HttpSession session, @RequestBody  PaymentForm form)
            throws  PlaceOrderException {
        ShoppingCart cart = getShoppingCart(principal, session);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(shoppingCartService.proceedCart(cart, form));
    }
}
