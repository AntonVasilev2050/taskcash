package com.avvsoft2050.taskfxi.controllers;

import com.avvsoft2050.taskfxi.services.PayService;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/pay.fxml")
public class ControllerPay {
    public TextField textFieldPaymentAmount;

    private final PayService payService;
    private final ControllerCashMain controllerCashMain;

    public ControllerPay(PayService payService, ControllerCashMain controllerCashMain) {
        this.payService = payService;
        this.controllerCashMain = controllerCashMain;
    }

    public void buttonPayClicked(ActionEvent actionEvent) {
        payService.finishPayment(controllerCashMain.labelTotal, controllerCashMain.vBoxCart, textFieldPaymentAmount);
    }
}
