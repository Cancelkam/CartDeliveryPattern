package ru.netology.delivery;

import lombok.Data;

@Data
public class CartData {
    private String city;
    private String date;
    private String name;
    private String phoneNumber;

    public CartData(String city, String date, String name, String phoneNumber) {
        this.city = city;
        this.date = date;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
