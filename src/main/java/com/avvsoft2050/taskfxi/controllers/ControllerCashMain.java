package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.model.Product;
import com.avvsoft2050.taskfxi.model.ProductInCart;
import com.avvsoft2050.taskfxi.services.ProductInCartServiceImpl;
import com.avvsoft2050.taskfxi.services.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    public ListView<Product> listViewProducts;
    public VBox vBoxCart;
    public Product selectedProduct;

    public void textFieldSelectAction() {
        String select = textFieldSelect.getText();
        selectedProduct = productService.findProduct(select);
        putProductIntoCart(selectedProduct);
    }

    private void putProductIntoCart(Product selectedProduct){
        if (selectedProduct == null) {
            return;
        }
        ProductInCart productInCart = productInCartService.findProduct(selectedProduct.getProductName());
        if (productInCart == null) {
            productInCart = new ProductInCart(0, selectedProduct.getProductName(), selectedProduct.getProductCost(), 1);
            productInCartService.saveProduct(productInCart);
        } else {
            productInCart.setQuantity(productInCart.getQuantity() + 1);
            productInCartService.saveProduct(productInCart);
        }
        showProductsInCart();
    }

    private void showProductsInCart() {
        productsInCart = productInCartService.getAllProductsSorted();
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
            productInCartHBox.getChildren().addAll(productInCartNameLabel, productInCartCostLabel,
                    productInCartQuantityLabel, productInCartAmountLabel, deleteProductInCart);
            vBoxCart.getChildren().add(productInCartHBox);
        }
    }

    public void buttonShowAllProductsClick() {
        List<Product> products = productService.getAllProducts();
        ObservableList<Product> productsObserve = FXCollections.observableList(products);
        listViewProducts.setItems(productsObserve);
        listViewProducts.setOnMouseClicked(event -> {
            selectedProduct = listViewProducts.getFocusModel().getFocusedItem();
            putProductIntoCart(selectedProduct);
        });
        showProductsInCart();
    }

}
