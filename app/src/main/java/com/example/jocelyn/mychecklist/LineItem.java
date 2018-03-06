package com.example.jocelyn.mychecklist;

public class LineItem {

    private Item item;
    private int quantity;
    private boolean checked;

    public LineItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}