package com.group.backend.demo.shoppingcart.domain;

import com.group.backend.demo.authentication.model.Address;
import com.group.backend.demo.authentication.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OrderedItems")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;

    @ManyToOne
    private User userID;


    public Order(){

    }
    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return userID;
    }

    public void setUser(User user) {
        this.userID = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @OneToMany(cascade = CascadeType.ALL)
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Temporal(TemporalType.DATE)
    private Date orderDate = new Date();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;


    public Order(User user, Address address){
        this.userID = user;
        this.address = address;
    }


    private double totalPrice;


    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem item){
        this.orderItems.add(item);
    }

    public void setAddress(Address address){
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderDate=" + orderDate +
                ", address=" + address +
                ", totalPrice=" + totalPrice +
                ", orderItems=" + orderItems +
                '}';
    }
}
