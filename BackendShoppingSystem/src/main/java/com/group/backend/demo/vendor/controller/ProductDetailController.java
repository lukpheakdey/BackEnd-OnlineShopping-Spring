package com.group.backend.demo.vendor.controller;

import com.group.backend.demo.vendor.domain.ProductDetail;
import com.group.backend.demo.vendor.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class ProductDetailController {

    @Autowired
    private IProductDetailService productDetailService;

    @GetMapping(value = "/productDetails")
    public List<ProductDetail> getAllProductDetails() {
        return (List<ProductDetail>) productDetailService.findAll();
    }

    @GetMapping(value = "/productDetail/{id}")
    public ProductDetail getProductDetailById(@PathVariable("id") Long productDetailId) {
        return productDetailService.findById(productDetailId);
    }

    @PostMapping(value = "/productDetail", produces = "application/json")
    public ProductDetail save(@Valid @RequestBody ProductDetail productDetail) {
        productDetailService.save(productDetail);
        return productDetail;
    }

    @DeleteMapping(value = "/productDetail/{id}")
    public ProductDetail delete(@PathVariable("id") Long productDetailId) {
        ProductDetail productDetail = productDetailService.findById(productDetailId);
        productDetailService.delete(productDetailId);
        return productDetail;
    }

    @GetMapping(value = "/getProductStockQuantity/{id}")
    public int getProductStockQuantity(@PathVariable("id") Long productId) {
        return productDetailService.getProductStockQuantity(productId);
    }
}


