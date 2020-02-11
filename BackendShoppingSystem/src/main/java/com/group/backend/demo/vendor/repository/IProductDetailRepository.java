package com.group.backend.demo.vendor.repository;


import com.group.backend.demo.vendor.domain.Product;
import com.group.backend.demo.vendor.domain.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    @Query("SELECT sum(d.quantity) FROM ProductDetail d where d.product.productId = ?1 group by d.product")
    int getProductStockQuantity(Long productId);


     ProductDetail findByProduct(Product product);



}