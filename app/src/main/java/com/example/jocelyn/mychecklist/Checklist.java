package com.example.jocelyn.mychecklist;

import java.util.ArrayList;

/**
 * Created by jacob on 3/23/18.
 */

public class Checklist {

    private ArrayList<LineItem> lineItems;

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }
}
