package com.group.backend.demo.vendor.service;

import com.group.backend.demo.vendor.domain.ProductDetail;

import java.util.List;

public interface IProductDetailService {

    public List<ProductDetail> findAll();

    public ProductDetail save(ProductDetail productDetail);

    public ProductDetail findById(Long productDetailId);

    public void delete(Long productDetailId);

    public int getProductStockQuantity(Long productId);


}