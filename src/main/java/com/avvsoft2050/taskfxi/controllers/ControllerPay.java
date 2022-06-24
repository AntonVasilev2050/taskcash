package com.avvsoft2050.taskfxi.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/pay.fxml")
public class ControllerPay {
    public Button buttonPay;
    public TextField textFieldPaymentAmount;
}
