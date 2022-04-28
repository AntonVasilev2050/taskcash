package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(int productId);
    Product findProduct(String productName);
    ArrayList<Product> getAllByProductNameIsLike(String productName);
}
