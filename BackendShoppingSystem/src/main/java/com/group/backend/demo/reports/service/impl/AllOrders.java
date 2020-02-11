package com.group.backend.demo.reports.service.impl;

import java.util.Date;


public class AllOrders {
    private Integer order_number;
    private String category_name;
    private Integer product_number;
    private String product_name;
    private String description;
    private Double total_price;
    private Integer quantity;
    private Date order_date;
    private Double summ;


    public AllOrders(Integer order_number, String category_name, Integer product_number, String product_name,
                     String description, Double total_price, Integer quantity, Date order_date, Double summ) {
        super();
        this.order_number = order_number;
        this.category_name = category_name;
        this.product_number = product_number;
        this.product_name = product_name;
        this.description = description;
        this.total_price = total_price;
        this.quantity = quantity;
        this.order_date = order_date;
        this.summ = summ;
    }
    public Integer getOrder_number() {
        return order_number;
    }
    public String getCategory_name() {
        return category_name;
    }
    public Integer getProduct_number() {
        return product_number;
    }
    public String getProduct_name() {
        return product_name;
    }
    public String getDescription() {
        return description;
    }
    public Double getTotal_price() {
        return total_price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public Double getSumm() {
        return summ;
    }
    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public void setProduct_number(Integer product_number) {
        this.product_number = product_number;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    public void setSumm(Double summ) {
        this.summ = summ;
    }



}
