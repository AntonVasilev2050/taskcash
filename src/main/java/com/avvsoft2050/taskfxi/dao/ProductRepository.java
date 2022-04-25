package com.avvsoft2050.taskfxi.dao;

import com.avvsoft2050.taskfxi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findFirstByProductNameEquals(String productName);
}
