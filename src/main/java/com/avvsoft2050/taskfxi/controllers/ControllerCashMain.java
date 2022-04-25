package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.model.Product;
import com.avvsoft2050.taskfxi.services.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FxmlView("/cash_main.fxml")
public class ControllerCashMain {

    @Autowired
    ProductServiceImpl productService;
    @FXML
    public TextField textFieldSelect;
    @FXML
    public ListView<String> listViewProducts;
    public VBox vBoxCart;

    public void showAllProducts(MouseEvent mouseEvent) {
        List<Product> products = productService.getAllProducts();
        List<String> productsList = new ArrayList<>();
        for (Product product : products) {
            productsList.add(product.toString());
        }
        ObservableList<String> productsObserve = FXCollections.observableList(productsList);
        listViewProducts.setItems(productsObserve);
    }

    public void textFieldSelectAction() {
        String select = textFieldSelect.getText();
        int quantity = 0;
        Map<String, Integer> productsInCart = new HashMap<>();
        Product product = productService.findProduct(select);
        if(productsInCart.containsKey(product.getProductName())){
            quantity = productsInCart.get(product);
            productsInCart.put(product.getProductName(), quantity++);
        }
        if (!(product == null)) {

            HBox productInCartHBox = new HBox();
            String productInCartName = product.getProductName();
            Label productInCartNameLabel = new Label(productInCartName);
            productInCartNameLabel.setPrefWidth(350.0);
            String productInCartCost = String.valueOf(product.getProductCost());
            Label productInCartCostLabel = new Label(productInCartCost);
            productInCartCostLabel.setPrefWidth(100.0);
            quantity++;
            Label productInCartQuantityLabel = new Label(String.valueOf(quantity));
            productInCartHBox.getChildren().addAll(productInCartNameLabel, productInCartCostLabel);
            vBoxCart.getChildren().add(productInCartHBox);
        }
    }
}
