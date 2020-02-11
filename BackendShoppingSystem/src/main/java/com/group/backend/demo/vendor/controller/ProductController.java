package com.group.backend.demo.vendor.controller;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.authentication.service.UserServiceImplementation;
import com.group.backend.demo.vendor.domain.Product;
import com.group.backend.demo.vendor.domain.ProductRequest;
import com.group.backend.demo.vendor.service.ICategoryService;
import com.group.backend.demo.vendor.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private UserServiceImplementation implementation;

    @Autowired
    private UserServiceImplementation userService;



    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping(value = "/productbyvendor")
    public List<Product> getProductByVendor(Principal principal) {
        return productService.findByVendor(userService.loadUserByUsername(principal.getName()));
    }




    @GetMapping(value = "/approved_products")
    public List<Product> getApprovedProduct(@RequestHeader Map<String, String> header) {
        return  productService.getApprovedProduct();
    }



    @GetMapping(value = "/product/{id}")
    public Product getProductById(@PathVariable("id") Long productId) {
        return productService.findById(productId);
    }



    @PostMapping(value = "/product", produces = "application/json")
    public Product save(@RequestBody ProductRequest product, Principal principal) {

        User user = implementation.loadUserByUsername(principal.getName());
        Product product1 = new Product(categoryService.findById(product.getCategoryId()));
        product.updateProduct(product1);
      //  product1.setUser(user); //vendor of the product
        product1.setStatus("unapproved");
        productService.save(product1, user);

        return product1;
    }


    @DeleteMapping(value = "/product/{id}")
    public Product delete(@PathVariable("id") Long productId) {
        Product product = productService.findById(productId);
        productService.delete(productId);
        return product;
    }

    @GetMapping("/products/search")
    public List<Product> search(@RequestParam("productNumber") String productNumber, @RequestParam("productName") String productName,
                                @RequestParam("minProductPrice") Double minProductPrice, @RequestParam("maxProductPrice")
                                        Double maxProductPrice, @RequestParam("status") Integer status) {
        boolean byProductNumber = productNumber != null && !productNumber.isEmpty();
        boolean byProductName = productName != null && !productName.isEmpty();
        boolean byMinPrice = minProductPrice != 0;
        boolean byMaxPrice = maxProductPrice != 0;
        boolean byStatus = status == 1;

        if (byProductNumber && byProductName && (byMinPrice || byMaxPrice)) {
            return productService.findByAll(productNumber, productName, minProductPrice, maxProductPrice, status);
        } else if (byProductNumber && byProductName) {
            return productService.findByNameNumber(productNumber, productName, status);
        } else if (byProductName && (byMinPrice || byMaxPrice)) {
            return productService.findByNamePrice(productName, minProductPrice, maxProductPrice, status);
        } else if (byProductNumber && (byMinPrice || byMaxPrice)) {
            return productService.findByNumberPrice(productNumber, minProductPrice, maxProductPrice, status);
        } else if (byProductName) {
            return productService.findByName(productName, status);
        } else if (byProductNumber) {
            return productService.findByNumber(productNumber, status);
        } else if ((byMinPrice || byMaxPrice)) {
            return productService.findByPrice(minProductPrice, maxProductPrice, status);
        } else if (byStatus) {
            return productService.findByStatus(status);
        } else
            return productService.findAll();

    }
}
