package com.example.jocelyn.mychecklist;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.jocelyn.mychecklist.api.APIAdapter;
import com.example.jocelyn.mychecklist.api.LoginAdapter;
import com.example.jocelyn.mychecklist.model.Checklist;
import com.example.jocelyn.mychecklist.model.Item;
import com.example.jocelyn.mychecklist.model.LineItem;
import com.example.jocelyn.mychecklist.model.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jacob on 4/17/18.
 */

public class Controller {

    private static Controller instance;
    private Checklist currentChecklist;
    private APIAdapter api;
    private RequestQueue queue;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public void setupAPI(Context context) {
        queue = Volley.newRequestQueue(context);
        api = new APIAdapter(queue);
    }


    public boolean login(String username, String password) {
        LoginAdapter loginAdapter = LoginAdapter.getInstance();

        if (loginAdapter.checkCredentials(username, password)) {
            User.getInstance().setUsername(username);
            return true;
        }

        return false;
    }

    public ArrayList<Checklist> getChecklists() {
        return User.getInstance().getChecklists();
    }

    public Checklist getChecklist(int i) {
        return User.getInstance().getChecklists().get(i);
    }

    public void addChecklist(Checklist checklist) {
        User.getInstance().getChecklists().add(checklist);
    }

    public Item search(String name) {
        final Item item = new Item();

        api.queryItem(name, new APIAdapter.SearchListener() {
            @Override
            public void onComplete(Item i) {
                //This method will be run when the search is finished.
                //i is an Item object. It will be the top/first item returned from the search

                item.setName(i.getName());
                item.setPrice(i.getPrice());
            }
        });

        return item;
    }

    public void addItem(String name, int quantity) {

        Item item = search(name);

        LineItem lineItem = new LineItem(item, quantity);

        currentChecklist.add(lineItem);
    }

    public void setCurrentChecklist(Checklist currentChecklist) {
        this.currentChecklist = currentChecklist;
    }

    public void refreshPrices() {
        for (LineItem lineItem : currentChecklist.getLineItems()) {
            String name = lineItem.getItem().getName();
            Item item = search(name);

            lineItem.setItem(item);
        }
    }

    public void clearChecklist() {
        currentChecklist.getLineItems().clear();
    }

    public void deleteCheckedItems() {
        Iterator<LineItem> iterator = currentChecklist.getLineItems().iterator();
        while (iterator.hasNext()) {
            LineItem lineItem = iterator.next();
            if (lineItem.isChecked()) {
                iterator.remove();
            }
        }
    }

    public void deleteChecklist(Checklist checklist) {
        User.getInstance().getChecklists().remove(checklist);
    }

    public void deleteChecklist(int i) {
        User.getInstance().getChecklists().remove(i);
    }

    public void editItem(int i, String name) {
        currentChecklist.getLineItems().get(i).getItem().setName(name);
        refreshPrices();
    }

    public LineItem getLineItem(int i) {
        return currentChecklist.getLineItems().get(i);
    }

    public ArrayList<LineItem> getLineItems() {
        return currentChecklist.getLineItems();
    }
}
