package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.entity.Product;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;

public interface CashMainService {
    void showProducts(List<Product> products,
                      ListView<Product> listViewProducts,
                      Label labelTotal,
                      VBox vBoxCart);

    void showProductsInCart(Label labelTotal, VBox vBoxCart);
    void pay(Label labelTotal, VBox vBoxCart);
}
