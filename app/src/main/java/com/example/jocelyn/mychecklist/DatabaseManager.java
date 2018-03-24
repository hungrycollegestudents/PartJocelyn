package com.example.jocelyn.mychecklist;

import java.util.ArrayList;

/**
 * Created by jacob on 3/23/18.
 */

public class DatabaseManager {

    private static DatabaseManager instance;

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }

    public ArrayList<Checklist> fetchChecklists() {
        return null;
    }

}
