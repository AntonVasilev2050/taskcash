package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.entity.Check;
import com.avvsoft2050.taskfxi.entity.CheckLine;
import com.avvsoft2050.taskfxi.entity.Product;
import com.avvsoft2050.taskfxi.pojos.ProductInCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashMainServiceImpl implements CashMainService {
    private final List<CheckLine> checkLines = new ArrayList<>();
    private int total = 0;
    private Product selectedProduct;


    final ProductService productService;
    final ProductInCartService productInCartService;
    final CheckService checkService;
    final CheckLineService checkLineService;

    public CashMainServiceImpl(ProductService productService,
                               ProductInCartService productInCartService,
                               CheckService checkService,
                               CheckLineService checkLineService) {
        this.productService = productService;
        this.productInCartService = productInCartService;
        this.checkService = checkService;
        this.checkLineService = checkLineService;
    }

    @Override
    public List<CheckLine> getCheckLines() {
        return checkLines;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public void showProducts(List<Product> products,
                              ListView<Product> listViewProducts,
                              Label labelTotal,
                              VBox vBoxCart) {
        ObservableList<Product> productsObserve = FXCollections.observableList(products);
        listViewProducts.setItems(productsObserve);
        listViewProducts.setOnMouseClicked(event -> {
            selectedProduct = listViewProducts.getFocusModel().getFocusedItem();
            addProductToCart(selectedProduct, labelTotal, vBoxCart);
        });
    }

    private void addProductToCart(Product selectedProduct, Label labelTotal, VBox vBoxCart) {
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
        showProductsInCart(labelTotal, vBoxCart);
    }

    @Override
    public void showProductsInCart(Label labelTotal, VBox vBoxCart) {
        List<ProductInCart> productsInCart = productInCartService.getAllProductsSorted();
        total = 0;
        int lineNumber = 0;
        labelTotal.setText(String.valueOf(total));
        vBoxCart.getChildren().clear();
        checkLines.clear();
        for (ProductInCart product : productsInCart) {
            lineNumber++;
            HBox productInCartHBox = new HBox();
            String productInCartName = product.getProductName();
            Label productInCartNameLabel = new Label(productInCartName);
            productInCartNameLabel.setPrefWidth(380.0);
            String productInCartCost = String.valueOf(product.getProductCost());
            Label productInCartCostLabel = new Label(productInCartCost);
            productInCartCostLabel.setPrefWidth(100.0);
            String productInCartQuantity = String.valueOf(product.getQuantity());
            Label productInCartQuantityLabel = new Label(productInCartQuantity);
            productInCartQuantityLabel.setPrefWidth(100);
            String lineAmount = String.valueOf(product.getProductCost() * product.getQuantity());
            Label productInCartAmountLabel = new Label(lineAmount);
            productInCartAmountLabel.setPrefWidth(100);
            Button productInCartDeleteButton = new Button("Удалить");

            CheckLine checkLine = new CheckLine();
            int productId = productService.findProduct(product.getProductName()).getProductId();
            checkLine.setProductId(productId);
            checkLine.setCheckId(0);
            checkLine.setLineNumber(lineNumber);
            checkLine.setQuantity(product.getQuantity());
            checkLine.setLineAmount(checkLine.getQuantity() * product.getProductCost());
            checkLines.add(checkLine);

            productInCartDeleteButton.setOnAction(event -> {
                productInCartService.deleteProductById(product.getProductId());
                showProductsInCart(labelTotal, vBoxCart);
            });
            total += product.getProductCost() * product.getQuantity();
            labelTotal.setText(String.valueOf(total));
            productInCartHBox.getChildren().addAll(
                    productInCartNameLabel,
                    productInCartCostLabel,
                    productInCartQuantityLabel,
                    productInCartAmountLabel,
                    productInCartDeleteButton);
            vBoxCart.getChildren().add(productInCartHBox);
        }
    }

    @Override
    public List<Product> filterProducts(List<Product> allProducts, TextField textFieldSelect) {
        String select = textFieldSelect.getText().trim();
        return allProducts
                .stream()
                .filter(product -> product.getProductName().contains(select)
                        ||
                        String.valueOf(product.getProductCost()).startsWith(select))
                .collect(Collectors.toList());
    }
}
