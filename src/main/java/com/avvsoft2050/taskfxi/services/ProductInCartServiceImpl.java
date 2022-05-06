package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.model.ProductInCart;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductInCartServiceImpl implements ProductInCartService {

    private final List<ProductInCart> productInCartList = new ArrayList<>();

    @Override
    public List<ProductInCart> getAllProductsSorted() {
        return productInCartList.stream().sorted(Comparator.comparingInt(ProductInCart::getProductId)).collect(Collectors.toList());
    }

    @Override
    public ProductInCart saveProduct(ProductInCart productInCart) {
        if (!productInCartList.contains(productInCart)) {
            productInCart.setProductId(productInCartList.size());
            System.out.println(productInCart.getProductId());
            productInCartList.add(productInCart);
        } else {
            int index = productInCartList.indexOf(productInCart);
            productInCart = productInCartList.get(index);
        }
        return productInCart;
    }

    @Override
    public void deleteProductById(int productId) {
        ProductInCart productInCartToRemove = null;
        for (ProductInCart productInCart : productInCartList) {
            if(productInCart.getProductId() == productId){
                productInCartToRemove = productInCart;
            }
        }
        if(productInCartToRemove != null){
            productInCartList.remove(productInCartToRemove);
        }
    }

    @Override
    public void deleteAllProducts() {
        productInCartList.clear();
    }

    @Override
    public ProductInCart getProductById(int productId) {
        return productInCartList.get(productId);
    }

    @Override
    public ProductInCart findProduct(String productInCartName) {
        for (ProductInCart productInCart : productInCartList) {
            String productName = productInCart.getProductName();
            if (productInCartName.equals(productName)) {
                return productInCart;
            }
        }
        return null;
    }
}
