package com.avvsoft2050.taskfxi.services;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public interface PayService {
    void startPayment();
    void finishPayment(Label labelTotal, VBox vBoxCart, TextField textFieldPaymentAmount);
}
