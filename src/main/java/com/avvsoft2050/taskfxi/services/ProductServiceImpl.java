package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.ProductRepository;
import com.avvsoft2050.taskfxi.model.Check;
import com.avvsoft2050.taskfxi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
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
