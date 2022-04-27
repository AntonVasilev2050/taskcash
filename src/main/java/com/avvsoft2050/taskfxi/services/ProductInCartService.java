package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.model.ProductInCart;

import java.util.List;
import java.util.Set;

public interface ProductInCartService {
    List<ProductInCart> getAllProductsSorted();
    ProductInCart saveProduct(ProductInCart productInCart);
    void deleteProductById(int productId);
    void deleteAllProducts();
    ProductInCart getProductById(int productId);
    ProductInCart findProduct(String productInCartName);
}
