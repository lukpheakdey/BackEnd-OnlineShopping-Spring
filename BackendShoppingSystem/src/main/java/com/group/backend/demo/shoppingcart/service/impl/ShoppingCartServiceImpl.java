package com.group.backend.demo.shoppingcart.service.impl;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.payment.model.PaymentForm;
import com.group.backend.demo.payment.service.IPaymentService;
import com.group.backend.demo.shoppingcart.domain.*;
import com.group.backend.demo.shoppingcart.exception.PlaceOrderException;
import com.group.backend.demo.shoppingcart.exception.ProductNotEnoughException;
import com.group.backend.demo.shoppingcart.exception.ProductNotFoundException;
import com.group.backend.demo.shoppingcart.repository.OrderRepository;
import com.group.backend.demo.shoppingcart.repository.ShoppingCartItemRepository;
import com.group.backend.demo.shoppingcart.repository.ShoppingCartRepository;
import com.group.backend.demo.shoppingcart.service.ShoppingCartService;
import com.group.backend.demo.vendor.domain.Product;
import com.group.backend.demo.vendor.domain.ProductDetail;
import com.group.backend.demo.vendor.repository.IProductDetailRepository;
import com.group.backend.demo.vendor.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group.backend.demo.shoppingcart.exception.ShoppingCartEmptyException;

import java.util.LinkedList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IProductDetailRepository productDetailRepository;

    @Autowired
    private IProductRepository iProductRepository;
    //autowire the payment service.
    @Autowired
    private IPaymentService paymentService;

    @Override
    public ShoppingCart findCart(User user) {
        return shoppingCartRepository.findByUser(user);
    }


    @Override
    public ShoppingCart createShoppingCart(User user){
        ShoppingCart cart = new ShoppingCart(user);
        shoppingCartRepository.save(cart);
        return cart;

    }


    @Override
    public void addProductToCart(ShoppingCart cart, Product product) {

        //avoid duplicates.
        if(cart.getShoppingCartItemList().stream()
                .filter(sci -> sci.getProduct().getProductId() == product.getProductId())
                .count() > 0)
            return;


        //add it to the cart.
        ShoppingCart cart1 = shoppingCartRepository.findById(cart.getCartId()).get();
        cart1.addProduct(product);
        shoppingCartRepository.save(cart);

        System.out.println("service " + cart1);
    }

    @Override
    public void removeProductFromCart(ShoppingCart cart, Product product) throws ProductNotFoundException {

        int n = cart.getShoppingCartItemList().size();
        for(int i = 0; i < n; i++) {
            if(cart.getShoppingCartItemList().get(i).getProduct().getProductId() == product.getProductId()){
                cart.getShoppingCartItemList().remove(i);
                shoppingCartRepository.save(cart);
                return;
            }
        }
        throw new ProductNotFoundException("No product with id in shopping cart");
    }

    @Override
    public void updateCartItem(ShoppingCart cart, Product product, int quantity) throws ProductNotEnoughException, ProductNotFoundException {

        int n = cart.getShoppingCartItemList().size();

        int totalQuantity = productDetailRepository.getProductStockQuantity(product.getProductId());
        for(int i = 0; i < n; i++) {

            ShoppingCartItem sci = cart.getShoppingCartItemList().get(i);
            if(sci.getProduct().getProductId() == product.getProductId()) {

                //awesome.
                if(quantity >= totalQuantity)
                    throw new ProductNotEnoughException("No enough product in stock");

                cart.getShoppingCartItemList().get(i).setQuantity(quantity);
                shoppingCartRepository.save(cart);
                return;
            }
        }
        throw new ProductNotFoundException("No product in shopping cart");
    }

    public OrderConfirmation convertItemsForConfirm(ShoppingCart cart) throws ShoppingCartEmptyException {

        List<ShoppingCartItem> cartItems = cart.getShoppingCartItemList();
        if(cartItems == null || cartItems.size() == 0)
            throw new ShoppingCartEmptyException();

        OrderConfirmation conf = new OrderConfirmation();
        for(ShoppingCartItem sci : cartItems)
            conf.addItem(sci);
        return conf;
    }


    //store items in order and order item.
    @Override
    public OrderConfirmation proceedCart(ShoppingCart cart, PaymentForm form) throws PlaceOrderException {

        List<ShoppingCartItem> shoppingCartItems = cart.getShoppingCartItemList();
        if(shoppingCartItems == null || shoppingCartItems.size() == 0 )
            throw new PlaceOrderException("No items in shooping cart");


        //get cart amount.
        double amount = cart.getCartAmount();

        Order order = new Order(cart.getUser(), form.getAddress());
        order.setTotalPrice(amount);

        //make payment
        if(!makePayment(form, amount))
            throw new PlaceOrderException("Payment failed!");


        order.setOrderItems(new LinkedList<>());

        for (int i = 0; i < shoppingCartItems.size(); i++) {

            Product product = shoppingCartItems.get(i).getProduct();


            int quantity = shoppingCartItems.get(i).getQuantity();

            OrderItem orderItem = new OrderItem(product);
            orderItem.setQuantity(quantity);
            orderItem.setStatus("Purchased");
            order.getOrderItems().add(orderItem);



            //product id.





            //substract quantity from product detail.
            ProductDetail productDetail = productDetailRepository.findByProduct(product);
            productDetail.setQuantity(productDetail.getQuantity() - orderItem.getQuantity());
            productDetailRepository.save(productDetail);


            // quantity from product.

            product.setQuantity(product.getQuantity() - orderItem.getQuantity());
            iProductRepository.save(product);

        }
               orderRepository.save(order);
             //here.
               shoppingCartRepository.delete(cart);


               OrderConfirmation confirmation = new OrderConfirmation();
               confirmation.setAddress(form.getAddress());
               confirmation.setOrderNumber(order.getOrderID().toString());
               confirmation.setTotalAmount(amount);
               confirmation.setDate(order.getOrderDate());

               return confirmation;
    }

       //make payment method. using payment service.
    public boolean makePayment(PaymentForm form, double amount){
        if(paymentService.makePayment(form.getCardNo(), form.getCardName(), form.getExpirationDate(),form.getCcv(),form.getCardType(), amount))
            return true;
        else return false;
    }

}
