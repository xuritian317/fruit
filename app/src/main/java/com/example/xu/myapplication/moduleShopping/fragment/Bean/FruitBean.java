package com.example.xu.myapplication.moduleShopping.fragment.Bean;

/**
 * Created by 逝 on 2017/09/14.
 */

public class FruitBean {
    private String fruit;//水果名
    private double price;//单价
    private int number;//购买数量

    public FruitBean() {
    }

    public FruitBean(String fruit, double price, int number) {
        this.fruit = fruit;
        this.price = price;
        this.number = number;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "FruitBean{" +
                "fruit='" + fruit + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}
