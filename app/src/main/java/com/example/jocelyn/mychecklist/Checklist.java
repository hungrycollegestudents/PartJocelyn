package com.example.jocelyn.mychecklist;

import java.util.ArrayList;

/**
 * Created by jacob on 3/23/18.
 */

public class Checklist {

    private ArrayList<LineItem> lineItems;
    private String name;

    public Checklist(String name) {
        this.name = name;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
