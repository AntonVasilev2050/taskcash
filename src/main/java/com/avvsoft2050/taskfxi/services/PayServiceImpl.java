package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.controllers.ControllerPay;
import com.avvsoft2050.taskfxi.entity.Check;
import com.avvsoft2050.taskfxi.entity.CheckLine;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PayServiceImpl implements PayService  {

    private final ConfigurableApplicationContext applicationContext;
    private final ProductInCartService productInCartService;
    private final CheckService checkService;
    private final CheckLineService checkLineService;
    private final CashMainService cashMainService;
    private final ControllerPay controllerPay;

    @Autowired
    public PayServiceImpl(ConfigurableApplicationContext applicationContext,
                          ProductInCartService productInCartService,
                          CheckService checkService,
                          CheckLineService checkLineService, CashMainService cashMainService, ControllerPay controllerPay) {
        this.applicationContext = applicationContext;
        this.productInCartService = productInCartService;
        this.checkService = checkService;
        this.checkLineService = checkLineService;
        this.cashMainService = cashMainService;
        this.controllerPay = controllerPay;
    }

    @Override
    public void pay(Label labelTotal, VBox vBoxCart) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ControllerPay.class);      //подключаем контроллер
        Scene scene = new Scene(root);
        Stage payStage = new Stage();
        payStage.setScene(scene);
        payStage.setTitle("Оплата-");
        payStage.show();
        controllerPay.buttonPay.setOnAction(event -> {
            int paymentAmount = 0;
            try {
                paymentAmount = Integer.parseInt(controllerPay.textFieldPaymentAmount.getText());
            } catch (Exception e) {
                controllerPay.textFieldPaymentAmount.setText("Ошибка ввода");
            }
            if (paymentAmount == cashMainService.getTotal()) {
                saveCheck();
                payStage.close();
                productInCartService.deleteAllProducts();
                cashMainService.showProductsInCart(labelTotal, vBoxCart);
            } else {
                if (paymentAmount != 0) {
                    controllerPay.textFieldPaymentAmount.setText("Не верная сумма");
                }
            }
        });
    }

    private void saveCheck() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Check newCheck = checkService.saveCheck(new Check(0, date, time, cashMainService.getTotal()));
        int newCheckId = newCheck.getCheckId();
        for (CheckLine checkLine : cashMainService.getCheckLines()) {
            checkLine.setCheckId(newCheckId);
            checkLineService.saveCheckLine(checkLine);
        }
    }
}
