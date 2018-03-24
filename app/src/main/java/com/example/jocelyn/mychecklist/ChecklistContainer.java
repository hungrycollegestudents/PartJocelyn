package com.example.jocelyn.mychecklist;

import java.util.ArrayList;

/**
 * Created by jacob on 3/23/18.
 */

public class ChecklistContainer {

    private ArrayList<Checklist> checklists;

    private static ChecklistContainer instance;

    public static ChecklistContainer getInstance() {
        if (instance == null) {
            instance = new ChecklistContainer();
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
