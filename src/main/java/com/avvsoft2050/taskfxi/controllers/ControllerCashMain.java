package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.model.Check;
import com.avvsoft2050.taskfxi.model.Product;
import com.avvsoft2050.taskfxi.model.ProductInCart;
import com.avvsoft2050.taskfxi.services.CheckServiceImpl;
import com.avvsoft2050.taskfxi.services.ProductInCartServiceImpl;
import com.avvsoft2050.taskfxi.services.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FxmlView("/cash_main.fxml")
public class ControllerCashMain {

    public Label labelTotal;
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductInCartServiceImpl productInCartService;

    @Autowired
    CheckServiceImpl checkService;

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
    public ListView<Product> listViewProducts;
    public VBox vBoxCart;
    public Product selectedProduct;
    private int total = 0;

    @FXML
    public void buttonShowAllProductsClick() {
        List<Product> products = productService.getAllProducts();
        showProducts(products);
    }

    private void showProducts(List<Product> products) {
        ObservableList<Product> productsObserve = FXCollections.observableList(products);
        listViewProducts.setItems(productsObserve);
        listViewProducts.setOnMouseClicked(event -> {
            selectedProduct = listViewProducts.getFocusModel().getFocusedItem();
            putProductIntoCart(selectedProduct);
        });
        showProductsInCart();
    }

    @FXML
    public void textFieldSelectReleased() {
        String select = textFieldSelect.getText().trim();
        System.out.println(select);
        List<Product> productsFiltered = productService.getAllProducts()
                .stream().filter(product -> product.getProductName().contains(select))
                .collect(Collectors.toList());
        try {
            showProducts(productsFiltered);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    private void putProductIntoCart(Product selectedProduct) {
        if (selectedProduct == null) {
            return;
        }
        ProductInCart productInCart = productInCartService.findProduct(selectedProduct.getProductName());
        if (productInCart == null) {
            productInCart = new ProductInCart(
                    0, selectedProduct.getProductName(), selectedProduct.getProductCost(), 1);
            productInCartService.saveProduct(productInCart);
        } else {
            productInCart.setQuantity(productInCart.getQuantity() + 1);
            productInCartService.saveProduct(productInCart);
        }
        showProductsInCart();
    }

    private void showProductsInCart() {
        productsInCart = productInCartService.getAllProductsSorted();
        total = 0;
        labelTotal.setText(String.valueOf(total));
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
            deleteProductInCart.setOnAction(event -> {
                productInCartService.deleteProductById(p.getProductId());
                showProductsInCart();
            });
            total += p.getProductCost() * p.getQuantity();
            labelTotal.setText(String.valueOf(total));
            productInCartHBox.getChildren().addAll(productInCartNameLabel, productInCartCostLabel,
                    productInCartQuantityLabel, productInCartAmountLabel, deleteProductInCart);
            vBoxCart.getChildren().add(productInCartHBox);
        }
    }

    public void buttonPayClicked() {
        Stage payStage = new Stage();
        Group pay = new Group();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        Label label = new Label("Сумма оплаты");
        TextField textFieldPaymentAmount = new TextField();
        textFieldPaymentAmount.setPromptText(" Введите сумму");
        Button buttonOK = new Button("Оплатить");
        hBox.getChildren().addAll(label, textFieldPaymentAmount, buttonOK);
        pay.getChildren().addAll(hBox);
        payStage.setScene(new Scene(pay, 420, 200));
        payStage.setTitle("Оплата");
        payStage.show();
        buttonOK.setOnAction(event -> {
            int paymentAmount = 0;
            try {
                paymentAmount = Integer.parseInt(textFieldPaymentAmount.getText());
            } catch (Exception e) {
                textFieldPaymentAmount.setText("Ошибка ввода");
            }
            if (paymentAmount == total) {
                saveCheck();
                payStage.close();
                productInCartService.deleteAllProducts();
                showProductsInCart();
            } else {
                if (paymentAmount != 0) {
                    textFieldPaymentAmount.setText("Не верная сумма");
                }
            }
        });
    }

    private void saveCheck() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        checkService.saveCheck(new Check(0, date, time, total));
        System.out.println("The check was saved " + date + " " + time);
    }
}
