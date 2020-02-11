package com.group.backend.demo.shoppingcart.domain;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.vendor.domain.Product;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<ShoppingCartItem> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }
    public void addProduct(Product product){
        ShoppingCartItem cartItem = new ShoppingCartItem( product, 1);
        shoppingCartItemList.add(cartItem);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

    @OneToOne
    private User user;



    public ShoppingCart(User user){
        this.user = user;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", shoppingCartItemList=" + shoppingCartItemList +
                ", user=" + user +
                '}';
    }


    public double getCartAmount(){
        double total = 0.0;
        for(ShoppingCartItem cartItem : shoppingCartItemList){
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();

        }
        return total;
    }


}
