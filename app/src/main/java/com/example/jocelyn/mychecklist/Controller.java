package com.example.jocelyn.mychecklist;

/**
 * Created by jacob on 4/17/18.
 */

public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }
}
