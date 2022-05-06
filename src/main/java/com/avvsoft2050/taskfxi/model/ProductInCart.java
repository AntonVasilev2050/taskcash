package com.avvsoft2050.taskfxi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInCart {

    private int productId;
    private String productName;
    private int productCost;
    private int quantity;
}
