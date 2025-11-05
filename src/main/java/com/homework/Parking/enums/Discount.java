package com.homework.Parking.enums;

import lombok.Getter;

@Getter
public enum Discount {
    No_Discount ("Без скидки", 0.0 , 0),
    Short_Discount ("Краткий срок", 0.05, 15),
    Medium_Discount ("Средний срок", 0.1, 30),
    Large_Discount ("Долгий срок", 0.15, 60);

    private final String discount;
    private final double discountRate;
    private final int minDays;

    Discount(String discount, double discountRate, int minDays) {
        this.discount = discount;
        this.discountRate = discountRate;
        this.minDays = minDays;
    }
}
