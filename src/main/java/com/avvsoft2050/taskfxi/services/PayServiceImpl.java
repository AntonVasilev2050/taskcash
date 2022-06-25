package com.avvsoft2050.taskfxi.services;

import com.avvsoft2050.taskfxi.controllers.ControllerPay;
import com.avvsoft2050.taskfxi.entity.Check;
import com.avvsoft2050.taskfxi.entity.CheckLine;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PayServiceImpl implements PayService  {

    private Scene scene;
    private Stage payStage;

    private final ConfigurableApplicationContext applicationContext;
    private final ProductInCartService productInCartService;
    private final CheckService checkService;
    private final CheckLineService checkLineService;
    private final CashMainService cashMainService;

    public PayServiceImpl(ConfigurableApplicationContext applicationContext,
                          ProductInCartService productInCartService,
                          CheckService checkService,
                          CheckLineService checkLineService,
                          CashMainService cashMainService) {
        this.applicationContext = applicationContext;
        this.productInCartService = productInCartService;
        this.checkService = checkService;
        this.checkLineService = checkLineService;
        this.cashMainService = cashMainService;
    }

    @Override
    public void startPayment() {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ControllerPay.class);      //подключаем контроллер
        scene = new Scene(root);
        payStage = new Stage();
        payStage.setScene(scene);
        payStage.setTitle("Оплата-");
        payStage.show();
    }

    @Override
    public void finishPayment(Label labelTotal, VBox vBoxCart, TextField textFieldPaymentAmount){
        int paymentAmount = 0;
        try {
            paymentAmount = Integer.parseInt(textFieldPaymentAmount.getText());
        } catch (Exception e) {
            textFieldPaymentAmount.setText("Ошибка ввода");
        }
        if (paymentAmount == cashMainService.getTotal()) {
            saveCheck();
            payStage.close();
            productInCartService.deleteAllProducts();
            cashMainService.showProductsInCart(labelTotal, vBoxCart);
        } else {
            if (paymentAmount != 0) {
                textFieldPaymentAmount.setText("Не верная сумма");
            }
        }
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
