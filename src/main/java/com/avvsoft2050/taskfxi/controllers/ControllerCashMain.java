package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.model.CheckLine;
import com.avvsoft2050.taskfxi.model.Product;
import com.avvsoft2050.taskfxi.model.ProductInCart;
import com.avvsoft2050.taskfxi.services.CheckLineServiceImpl;
import com.avvsoft2050.taskfxi.services.ProductInCartServiceImpl;
import com.avvsoft2050.taskfxi.services.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@FxmlView("/cash_main.fxml")
public class ControllerCashMain {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductInCartServiceImpl productInCartService;

    List<ProductInCart> productsInCart = new ArrayList<>();

    public HBox vBoxCartBar;
    public Label productId;
    public Label productCost;
    public Label productAmount;
    public Label totalAmount;
    public Label deleteYes;
    @FXML
    public TextField textFieldSelect;
    @FXML
    public ListView<String> listViewProducts;
    public VBox vBoxCart;

    public void showAllProducts() {
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
        if (product == null) {
            return;
        }
        if(productInCartService.findProduct(product.getProductName()) == null){
            ProductInCart productInCart = new ProductInCart(0, product.getProductName(), product.getProductCost(), 1);
            productInCartService.saveProduct(productInCart);
        }
        else {
            ProductInCart productInCart = productInCartService.findProduct(product.getProductName());
            productInCart.setQuantity(productInCart.getQuantity() + 1);
            productInCartService.saveProduct(productInCart);
        }
        productsInCart = productInCartService.getAllProducts();
        vBoxCart.getChildren().clear();
        for (ProductInCart p : productsInCart) {
            HBox productInCartHBox = new HBox();
            String productInCartName = p.getProductName();
            Label productInCartNameLabel = new Label(productInCartName);
            productInCartNameLabel.setPrefWidth(380.0);
            String productInCartCost = String.valueOf(p.getProductCost());
            Label productInCartCostLabel = new Label(productInCartCost);
            productInCartCostLabel.setPrefWidth(100.0);
            String productInCartQuantity = String.valueOf(p.getQuantity());
            Label productInCartQuantityLabel = new Label(productInCartQuantity);
            productInCartQuantityLabel.setPrefWidth(100);
            String totalAmount = String.valueOf(p.getProductCost() * p.getQuantity());
            Label productInCartAmountLabel = new Label(totalAmount);
            productInCartAmountLabel.setPrefWidth(100);
            Button deleteProductInCart = new Button("Удалить");
            productInCartHBox.getChildren().addAll(productInCartNameLabel, productInCartCostLabel,
                    productInCartQuantityLabel, productInCartAmountLabel, deleteProductInCart);
            vBoxCart.getChildren().add(productInCartHBox);
        }
    }
}
