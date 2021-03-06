package com.avvsoft2050.taskfxi.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "checklines")
public class CheckLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkline_id")
    private int checkLineId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "check_id")
    private int checkId;

    @Column(name = "line_number")
    private int lineNumber;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "line_amount")
    private int lineAmount;
}
