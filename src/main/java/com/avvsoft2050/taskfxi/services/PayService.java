package com.avvsoft2050.taskfxi.services;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public interface PayService {
    void pay(Label labelTotal, VBox vBoxCart);
}
