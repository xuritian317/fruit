package com.example.xu.myapplication.moduleShopping.fragment.bean;

/**
 * Created by 逝 on 2017/09/14.
 */

public class FruitBean {
    private String fruit;//水果名
    private double price;//单价
    private int number;//购买数量
    private String fruit_img;//水果图片网址
    private boolean isChecked;//在购物车中是否选中  默认false


    public FruitBean() {
    }

    public FruitBean(String fruit, double price, int number, String fruit_img) {
        this.fruit = fruit;
        this.price = price;
        this.number = number;
        this.fruit_img = fruit_img;
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

    public String getFruit_img() {
        return fruit_img;
    }

    public void setFruit_img(String fruit_img) {
        this.fruit_img = fruit_img;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "FruitBean{" +
                "fruit='" + fruit + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", fruit_img='" + fruit_img + '\'' +
                '}';
    }
}
