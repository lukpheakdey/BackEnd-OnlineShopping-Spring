package com.group.backend.demo.vendor.service;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.vendor.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();

    public Product save(Product product, User user);

    public Product findById(Long productId);

    public void delete(Long productId);

    public List<Product> findByAll(String productNumber, String productName, Double minProductPrice, Double maxProductPrice, Integer status);

    public List<Product> findByNameNumber(String productNumber, String productName, Integer status);

    public List<Product> findByNamePrice(String productName, Double minProductPrice, Double maxProductPrice, Integer status);

    public List<Product> findByNumberPrice(String productNumber, Double minProductPrice, Double maxProductPrice, Integer status);

    public List<Product> findByName(String productName, Integer status);

    public List<Product> findByNumber(String productNumber, Integer status);

    public List<Product> findByStatus(Integer status);

    public List<Product> findByPrice(Double minProductPrice, Double maxProductPrice, Integer status);

}
