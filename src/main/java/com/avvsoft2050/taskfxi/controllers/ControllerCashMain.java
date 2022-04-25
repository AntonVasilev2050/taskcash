package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.model.CheckLine;
import com.avvsoft2050.taskfxi.model.Product;
import com.avvsoft2050.taskfxi.services.CheckLineServiceImpl;
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

    @Autowired
    CheckLineServiceImpl checkLineService;

    private final Map<String, Integer> productsInCart = new HashMap<>();
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
        Product product = productService.findProduct(select);
        List<CheckLine> checkLineList = checkLineService.getAllCheckLines();
        System.out.println(checkLineList.toString());
        if (checkLineList.isEmpty()) {
            checkLineService.saveCheckLine(new CheckLine(
                    0,
                    product.getProductId(),
                    0,
                    0,
                    1,
                    product.getProductCost()));
        } else {
            for (CheckLine checkLine : checkLineList) {
                if (product.getProductId() == checkLine.getProductId()) {
                    checkLine.setQuantity(checkLine.getQuantity() + 1);
                    checkLine.setLineAmount(product.getProductCost() * checkLine.getQuantity());
                    checkLineService.saveCheckLine(checkLine);
                }
            }
        }
        checkLineList = checkLineService.getAllCheckLines();
        vBoxCart.getChildren();
        for (CheckLine checkLine : checkLineList) {
            int productId = checkLine.getProductId();
            product = productService.getProduct(productId);
            HBox productInCartHBox = new HBox();
            String productInCartName = String.valueOf(product.getProductName());
            Label productInCartNameLabel = new Label(productInCartName);
            productInCartNameLabel.setPrefWidth(380.0);
            String productInCartCost = String.valueOf(product.getProductCost());
            Label productInCartCostLabel = new Label(productInCartCost);
            productInCartCostLabel.setPrefWidth(100.0);
            String productInCartQuantity = String.valueOf(checkLine.getQuantity());
            Label productInCartQuantityLabel = new Label(productInCartQuantity);
            productInCartQuantityLabel.setPrefWidth(100);
            productInCartHBox.getChildren().addAll(productInCartNameLabel, productInCartCostLabel, productInCartQuantityLabel);
            vBoxCart.getChildren().add(productInCartHBox);
        }
    }
}
