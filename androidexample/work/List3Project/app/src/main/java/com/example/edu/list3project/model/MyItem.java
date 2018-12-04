package com.example.edu.list3project.model;

import java.io.Serializable;

public class MyItem implements Serializable {
    public int imgRes;
    public String title;
    public String type;
    public int price;

    public MyItem(){}
    public MyItem(int imgRes, String title, String type, int price) {
        this.imgRes = imgRes;
        this.title = title;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "imgRes=" + imgRes +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
