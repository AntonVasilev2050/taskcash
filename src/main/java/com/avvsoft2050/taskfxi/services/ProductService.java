package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product findProduct(String productName);
}
