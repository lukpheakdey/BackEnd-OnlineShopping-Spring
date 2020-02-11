package com.group.backend.demo.shoppingcart.domain;

public class UpdateRequest {


    private int qty;
    private long productId;

    public UpdateRequest(){

    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "UpdateRequest{" +
                "qty=" + qty +
                ", productId=" + productId +
                '}';
    }
}
