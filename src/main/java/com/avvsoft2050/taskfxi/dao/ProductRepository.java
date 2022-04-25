package com.avvsoft2050.taskfxi.dao;

import com.avvsoft2050.taskfxi.model.Product;
import com.avvsoft2050.taskfxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findFirstByProductNameEquals(String productName);
}
