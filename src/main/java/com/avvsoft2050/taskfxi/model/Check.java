package com.avvsoft2050.taskfxi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "checks")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_id")
    private int checkId;

    @Column(name = "check_date")
    private LocalDate checkDate;

    @Column(name = "check_time")
    private LocalTime checkTime;

    @Column(name = "check_amount")
    private int checkAmount;
}
