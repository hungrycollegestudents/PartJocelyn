package com.example.jocelyn.mychecklist.api;

import android.util.Log;

/**
 * Created by jacob on 4/17/18.
 */

public class LoginAdapter {

    private static LoginAdapter instance;

    private LoginAdapter() {}

    public static LoginAdapter getInstance() {
        if (instance == null) {
            instance = new LoginAdapter();
        }
        return instance;
    }

    public boolean checkCredentials(String username, String password) {
        if (username.equalsIgnoreCase("test")) {
            if (password.equals("abc123")) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

}
