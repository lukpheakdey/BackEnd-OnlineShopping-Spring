package com.group.backend.demo.shoppingcart.domain;

public class CartItemResponse {


    private int productId;
    private String productName;
    private int quantity;
    private String description;
    private double price;
    private double total;
    private String productImage;

    public CartItemResponse() {

    }

    public CartItemResponse(int productId, String productName, int quantity, String description, double price, double total, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.total = total;
        this.productImage = productImage;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "CartItemResponse{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}
