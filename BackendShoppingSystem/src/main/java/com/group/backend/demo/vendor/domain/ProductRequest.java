package com.group.backend.demo.vendor.domain;

public class ProductRequest {

    private long categoryId;

    private String description;

    private double  price;

    private String productName;

    private String productNumber;

    private int quantity;

    private String productImage;




    public void updateProduct(Product product){
        product.setDescription(description);
        product.setPrice(price);
        product.setProductName(productName);
        product.setProductNumber(productNumber);
        product.setQuantity(quantity);
        product.setProductImage(productImage);

    }







    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductRequest(){

    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                ", productNumber='" + productNumber + '\'' +
                '}';
    }
}
