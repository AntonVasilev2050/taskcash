package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.ProductRepository;
import com.avvsoft2050.taskfxi.model.Check;
import com.avvsoft2050.taskfxi.model.Product;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().sorted(Comparator.comparingInt(Product::getProductId)).collect(Collectors.toList());
    }

    @Override
    public Product getProduct(int productId) {
        Product product = new Product();
        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()){
            product = optional.get();
        }
        return product;
    }

    @Override
    public Product findProduct(String productName) {
        return productRepository.findFirstByProductNameEquals(productName);
    }
}
