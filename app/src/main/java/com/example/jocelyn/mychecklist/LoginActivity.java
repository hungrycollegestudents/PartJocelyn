package com.example.jocelyn.mychecklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import android.R;

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
       /* Intent intent = new Intent(this, ListMenuActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/
    }
}
