package com.group.backend.demo.reports.service.impl;


public class AllProduct {
    private String category_name;
    private int product_number;
    private String product_name;
    private String description;
    private double price;
    private int quantity;
    private double total;
    private double sum;

    public AllProduct(String category_name, int product_number, String product_name, String description, double price, int quantity, double total, double sum) {
        this.category_name = category_name;
        this.product_number = product_number;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.sum = sum;
    }

    public AllProduct(String category_name, int product_number, String product_name, String description, double price,
                      int quantity, double total) {
        super();
        this.category_name = category_name;
        this.product_number = product_number;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getProduct_number() {
        return product_number;
    }

    public void setProduct_number(int product_number) {
        this.product_number = product_number;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
