package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.ProductInCartRepository;
import com.avvsoft2050.taskfxi.model.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductInCartServiceImpl implements ProductInCartService{

    @Autowired
    ProductInCartRepository productInCartRepository;

    @Override
    public List<ProductInCart> getAllProductsSorted() {
        return productInCartRepository.findAll().stream()
                .sorted(Comparator.comparingInt(ProductInCart::getProductId)).collect(Collectors.toList());

    }

    @Override
    public ProductInCart saveProduct(ProductInCart productInCart) {
       return productInCartRepository.save(productInCart);
    }

    @Override
    public void deleteProductById(int productId) {
        productInCartRepository.deleteById(productId);
    }

    @Override
    public void deleteAllProducts() {
        productInCartRepository.deleteAll();
    }

    @Override
    public ProductInCart getProductById(int productId) {
        ProductInCart productInCart = new ProductInCart();
        Optional<ProductInCart> optional = productInCartRepository.findById(productId);
        if(optional.isPresent()){
            productInCart = optional.get();
        }
        return productInCart;
    }

    @Override
    public ProductInCart findProduct(String productInCartName) {
        return productInCartRepository.findFirstByProductNameEquals(productInCartName);
    }
}
