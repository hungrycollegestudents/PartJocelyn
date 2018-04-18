package com.example.jocelyn.mychecklist.model;

import java.util.ArrayList;

/**
 * Created by jacob on 3/23/18.
 */

public class User {

    private ArrayList<Checklist> checklists;
    private String username;

    private static User instance;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }

        return instance;
    }

    public ArrayList<Checklist> getChecklists() {
        return checklists;
    }

    public void addChecklist(Checklist checklist) {
        checklists.add(checklist);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
