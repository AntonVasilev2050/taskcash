package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.dao.ProductRepository;
import com.avvsoft2050.taskfxi.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Product::getProductId))
                .collect(Collectors.toList());
    }

    @Override
    public Product findProduct(String productName) {
        return productRepository.findFirstByProductNameEquals(productName);
    }
}
