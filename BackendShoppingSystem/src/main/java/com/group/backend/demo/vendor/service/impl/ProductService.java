package com.group.backend.demo.vendor.service.impl;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.vendor.domain.Product;
import com.group.backend.demo.vendor.domain.ProductDetail;
import com.group.backend.demo.vendor.repository.IProductRepository;
import com.group.backend.demo.vendor.service.IProductDetailService;
import com.group.backend.demo.vendor.service.IProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class ProductService implements IProductService {
    private IProductRepository productRepository;

    @Autowired
    private IProductDetailService productDetailService;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getApprovedProduct(){
        return productRepository.getAllByStatus("approved");
    }

    public List<Product> findByVendor(User user){
        return productRepository.findAllByUser(user);
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByAll(String productNumber, String productName, Double minProductPrice, Double maxProductPrice, Integer status) {
        return productRepository.findByAll(productNumber, productName, minProductPrice, maxProductPrice, status);
    }


    @Override
    public List<Product> findByNameNumber(String productNumber, String productName, Integer status) {
        return productRepository.findByNameNumber(productNumber, productName, status);
    }

    @Override
    public List<Product> findByNamePrice(String productName, Double minProductPrice, Double maxProductPrice, Integer status) {
        return productRepository.findByNamePrice(productName, minProductPrice, maxProductPrice, status);
    }

    @Override
    public List<Product> findByNumberPrice(String productNumber, Double minProductPrice, Double maxProductPrice, Integer status) {
        return productRepository.findByNumberPrice(productNumber, minProductPrice, maxProductPrice, status);
    }

    @Override
    public List<Product> findByName(String productName, Integer status) {
        return productRepository.findByName(productName, status);
    }

    @Override
    public List<Product> findByNumber(String productNumber, Integer status) {
        return productRepository.findByNumber(productNumber, status);
    }

    @Override
    public List<Product> findByStatus(Integer status) {
        return productRepository.findByStatus(status);
    }

    @Override
    public List<Product> findByPrice(Double minProductPrice, Double maxProductPrice, Integer status) {
        return productRepository.findByPrice(minProductPrice, maxProductPrice, status);
    }

    @Override
    public Product save(Product product, User user) {

        productRepository.save(product);
        product.setUser(user);
//save product detail.
        productDetailService.save(new ProductDetail(product.getDescription(),product.getDescription(), product.getQuantity(), product, user));


        return product;
    }

    @Override
    public Product findById(Long productId) {


        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

}