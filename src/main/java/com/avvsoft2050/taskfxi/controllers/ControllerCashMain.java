package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.model.Product;
import com.avvsoft2050.taskfxi.services.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("/cash_main.fxml")
public class ControllerCashMain {

    @Autowired
    ProductServiceImpl productService;
    @FXML
    public TextField textFieldSelect;
    @FXML
    public ListView<Product> listViewProducts;
    public HBox vBoxCartBar;

    public void showAllProducts(MouseEvent mouseEvent) {
        List<Product> products = productService.getAllProducts();
        ObservableList<Product> productsObserve = FXCollections.observableList(products);
        listViewProducts.setItems(productsObserve);
    }

    public void textFieldSelectAction() {
        String select = textFieldSelect.getText();
        System.out.println(select);
        Product product = productService.findProduct(select);
        System.out.println(product.toString());
        if(!(product == null)){
            vBoxCartBar.getChildren().add(new HBox());
        }
    }
}
