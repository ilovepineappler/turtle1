package com.example.edu.netproject;

import java.util.ArrayList;

public class ProductList {
    ArrayList<Product> list = new ArrayList<Product>();

    public ArrayList<Product> getList() {
        return list;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "list=" + list +
                '}';
    }
}
