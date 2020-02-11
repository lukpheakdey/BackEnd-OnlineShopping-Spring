package com.group.backend.demo.vendor.domain;


import com.group.backend.demo.authentication.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;


    @Column(name = "product_number")
    private String productNumber;


    @Column(name = "product_name")
    private String productName;


    @Column(name = "price")
    private double price;

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "description")
    private String description;

    private String status; // 0- inactive,  1- active



    @ManyToOne
    private User user; //vendor of the product.



    public void setUser(User user) {
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    private String productImage;

    private int quantity; //number of products in the stock.

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;


    public Product(Category category){
        this.category = category;
    }

    public Product(String productName, Double price, String description, String status, String imagePath, Category category) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.status = status;
        this.productImage = imagePath;
        this.category = category;
    }


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", productImage='" + productImage + '\'' +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}