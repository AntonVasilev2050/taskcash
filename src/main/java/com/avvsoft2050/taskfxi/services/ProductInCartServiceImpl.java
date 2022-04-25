package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.ProductInCartRepository;
import com.avvsoft2050.taskfxi.dao.ProductRepository;
import com.avvsoft2050.taskfxi.model.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductInCartServiceImpl implements ProductInCartService{

    @Autowired
    ProductInCartRepository productInCartRepository;

    @Override
    public List<ProductInCart> getAllProducts() {
        return productInCartRepository.findAll();
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
