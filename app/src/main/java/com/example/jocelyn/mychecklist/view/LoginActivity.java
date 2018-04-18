package com.example.jocelyn.mychecklist.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import android.R;

import com.example.jocelyn.mychecklist.Controller;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.jocelyn.mychecklist.R.layout.activity_login);


    }

    /*
        Function: submitLoginCredentials

        Submits login credentials and starts next Activity
     */

    public void submitLoginCredentials(View view) {
        if (Controller.getInstance().login(null, null)) {
            Intent intent = new Intent(this, ChecklistsActivity.class);
            startActivity(intent);
        }
    }
}
