package com.avvsoft2050.taskfxi.dao;

import com.avvsoft2050.taskfxi.model.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, Integer> {
    ProductInCart findFirstByProductNameEquals(String productName);
}
