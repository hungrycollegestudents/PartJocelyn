package com.example.jocelyn.mychecklist;

import android.os.Build;

import com.example.jocelyn.mychecklist.api.LoginAdapter;
import com.example.jocelyn.mychecklist.model.Checklist;
import com.example.jocelyn.mychecklist.model.User;

import java.util.ArrayList;

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
}
