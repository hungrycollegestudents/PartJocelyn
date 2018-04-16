package com.example.jocelyn.mychecklist;

import java.util.ArrayList;

/**
 * Created by jacob on 3/23/18.
 */

public class User {

    private ArrayList<Checklist> checklists;
`
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
}
