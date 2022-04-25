package com.avvsoft2050.taskfxi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "goods")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_cost")
    private int productCost;

    @Override
    public String toString() {
        return " " + productId + "  " + productName + "  " + productCost;
    }
}
