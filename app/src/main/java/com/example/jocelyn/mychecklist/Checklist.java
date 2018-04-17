package com.example.jocelyn.mychecklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jacob on 3/23/18.
 */

public class Checklist implements Serializable {

    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
    private String name;
    private int id;

    public Checklist(String name) {
        this.name = name;
        this.id = new Random().nextInt(1000000);
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void add(LineItem item) {
        lineItems.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
