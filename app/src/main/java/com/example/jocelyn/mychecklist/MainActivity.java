package com.example.jocelyn.mychecklist;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //ArrayList<String> items = new ArrayList<>();
    //ArrayAdapter<String> itemsArray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

    ListView listView;
    ArrayList<LineItem> itemsArray = new ArrayList<>();
    ItemAdapter adapter;

    RequestQueue queue;
    APIAdapter api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listItems);
        adapter = new ItemAdapter(this, itemsArray);
        listView.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        queue = Volley.newRequestQueue(this.getApplicationContext());

        //Create object that adapts http json API (probably should change to singleton)
        api = new APIAdapter(queue);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checklist_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_addItem:
                promptAddItem();
                return true;

            case R.id.action_deleteItem:
                return true;

            case R.id.action_refreshPrices:
                return true;
        }
        return false;

    }

    public void promptAddItem(){
        AddItemDialog newFragment = new AddItemDialog();
        newFragment.thingy = new AddItemDialog.Thingy() {
            @Override
            public void idk(String name, int quantity) {
                System.out.println("WOW!");
                addItem(name, quantity);
            }
        };
        newFragment.show(getFragmentManager(), "adding");
        //newFragment.show(getSupportFragmentManager(), "adding");
    }

    public void addItem(String name, int quantity) {

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

        LineItem lineItem = new LineItem(item, quantity);

        itemsArray.add(lineItem);
        adapter.notifyDataSetChanged();
    }

    public void clearList(){
        adapter.clear();
    }


    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    /*
    public void addItemPrompt(View view){
        final EditText addNewItemText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new Item")
                .setView(addNewItemText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        String item = String.valueOf(addNewItemText.getText());
                        Log.d(TAG, "Item to add: " + item);
                    }
                })
                .setNegativeButton("Cancel" , null)
                .create();
        dialog.show();
    }
    */

}
