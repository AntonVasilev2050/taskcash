package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.model.ProductInCart;

import java.util.List;

public interface ProductInCartService {
    List<ProductInCart> getAllProductsSorted();
    ProductInCart saveProduct(ProductInCart productInCart);
    void deleteProductById(int productId);
    void deleteAllProducts();
    ProductInCart findProduct(String productInCartName);
}
