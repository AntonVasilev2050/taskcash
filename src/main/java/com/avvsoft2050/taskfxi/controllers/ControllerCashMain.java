package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.entity.Product;
import com.avvsoft2050.taskfxi.services.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/cash_main.fxml")
public class ControllerCashMain implements Initializable {

    final ProductService productService;
    final CashMainService cashMainService;
    final PayService payService;

    public Label labelTotal;
    public TextField textFieldSelect;
    public ListView<Product> listViewProducts;
    public VBox vBoxCart;

    private List<Product> allProducts = new ArrayList<>();

    @Autowired
    public ControllerCashMain(ProductService productService,
                              CashMainService cashMainService,
                              PayService payService) {
        this.productService = productService;
        this.cashMainService = cashMainService;
        this.payService = payService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allProducts = productService.getAllProducts();
        cashMainService.showProducts(allProducts, listViewProducts, labelTotal, vBoxCart);
        cashMainService.showProductsInCart(labelTotal, vBoxCart);
    }

    public void buttonShowAllProductsClicked() {
        allProducts = productService.getAllProducts();
        cashMainService.showProducts(allProducts, listViewProducts, labelTotal, vBoxCart);
    }

    public void textFieldSelectReleased() {
        List<Product> filteredProducts =cashMainService.filterProducts(allProducts, textFieldSelect);
        cashMainService.showProducts(filteredProducts, listViewProducts, labelTotal, vBoxCart);
    }

    public void buttonPayClicked() {
        payService.startPayment();

    }
}