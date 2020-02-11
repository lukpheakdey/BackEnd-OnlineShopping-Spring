package com.group.backend.demo.shoppingcart.domain;

import com.group.backend.demo.authentication.model.Address;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderConfirmation {
    private List<ShoppingCartItem> shoppingCartItemList;
    private double totalAmount;
    private double tax;

    //for confirmation poage.
    private String orderNumber;
    private String totalPrice;
    private Date date;


    //address.
    //address fields.
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;



    public void setAddress(Address address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.zip = address.getZip();
        this.country = address.getCountry();

    }




    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public OrderConfirmation() {
        shoppingCartItemList = new LinkedList<>();
        totalAmount = 0.0;
        tax = 0.0;
    }

    public List<ShoppingCartItem> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getTax() {
        return tax;
    }

    public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }

    public void addItem(ShoppingCartItem item) {

        this.shoppingCartItemList.add(item);

        double price = item.getProduct().getPrice() * item.getQuantity();
        totalAmount += price;
        tax += price * 0.07;
    }
}
