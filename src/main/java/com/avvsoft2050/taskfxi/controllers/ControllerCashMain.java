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
import java.util.List;

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
        for (Product product: products){
            productsList.add(product.toString());
        }
        ObservableList<String> productsObserve = FXCollections.observableList(productsList);
        listViewProducts.setItems(productsObserve);
    }

    public void textFieldSelectAction() {
        String select = textFieldSelect.getText();
        System.out.println(select);
        Product product = productService.findProduct(select);
        System.out.println(product.toString());
        if(!(product == null)){
            HBox productInCartHBox = new HBox();
            String productInCartId = String.valueOf(product.getProductId());
            Label productInCartIdLabel = new Label(productInCartId);
            String productInCartName = product.getProductName();
            Label productInCartNameLabel = new Label(productInCartName);
            String productInCartCost = String.valueOf(product.getProductCost());
            Label productInCartCostLabel = new Label(productInCartCost);
            productInCartHBox.getChildren().addAll(productInCartIdLabel, productInCartNameLabel, productInCartCostLabel);
            vBoxCart.getChildren().add(productInCartHBox);
        }
    }
}
