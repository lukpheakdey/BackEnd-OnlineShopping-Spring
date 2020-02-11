package com.group.backend.demo.shoppingcart.domain;

public class CartRequest {

    private long userId;
    private long productId;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
