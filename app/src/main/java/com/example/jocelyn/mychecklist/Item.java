package com.example.jocelyn.mychecklist;

public class Item {

    private String name;
    private Price price;
    private int upc;

    public Item() {

    }

    public Item(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
