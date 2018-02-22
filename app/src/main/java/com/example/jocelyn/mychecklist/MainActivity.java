package com.example.jocelyn.mychecklist;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //ArrayList<String> items = new ArrayList<>();
    //ArrayAdapter<String> itemsArray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

    ListView listView;
    ArrayList<Item> itemsArray = new ArrayList<>();
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listItems);
        adapter = new ItemAdapter(this, itemsArray);
        listView.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
            public void idk(Item item) {
                System.out.println("WOW!");
                addItem(item.name, item.quantity);
            }
        };
        newFragment.show(getFragmentManager(), "adding");
        //newFragment.show(getSupportFragmentManager(), "adding");
    }

    public void addItem(String name, int quantity){
        Item newItem = new Item(name, quantity);
        itemsArray.add(newItem);
        adapter.notifyDataSetChanged();
        //adapter.add(newItem);
    }

    public void clearList(){
        adapter.clear();
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
