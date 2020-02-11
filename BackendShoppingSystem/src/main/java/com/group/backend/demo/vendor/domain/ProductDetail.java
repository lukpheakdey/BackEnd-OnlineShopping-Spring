package com.group.backend.demo.vendor.domain;



import com.group.backend.demo.authentication.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private long detailId;

    @NotNull(message = "*Product detail name is required")
    @Column(name = "detail_name")
    private String detailName;

    private String description;

    @NotNull
    private int quantity;

    @OneToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;


    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName="id",  nullable = false)
    private User userId; //vendor of the product.

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDetail(@NotNull @NotEmpty String detailName, String description, int quantity, Product product, User user) {
        this.detailName = detailName;
        this.description = description;
        this.quantity = quantity;
        this.product = product;
        this.userId = user;

    }

    public void setProduct(Product product){
        this.product = product;

    }

    public Product getProduct() {
        return product;
    }
}