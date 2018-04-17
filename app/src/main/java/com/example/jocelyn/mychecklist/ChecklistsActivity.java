package com.example.jocelyn.mychecklist;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Iterator;

public class ChecklistsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Checklist> checklists = new ArrayList<>();
    ChecklistAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklists);


        listView = (ListView) findViewById(R.id.listChecklists);

        adapter = new ChecklistAdapter(this, checklists);
        listView.setAdapter(adapter);

        //Checklist cl = (Checklist) getIntent().getSerializableExtra("checklist");

//        if (cl != null) {
//            System.out.println("TEST");
//            //System.out.println(cl.getName());
//            for (Checklist c : checklists) {
//                if (c.getId() == cl.getId()) {
//                    c.setLineItems(cl.getLineItems());
//                    break;
//                }
//            }
//        } else {
//            addChecklist("Test Checklist");
//        }

        addChecklist("Test Checklist");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Checklist checklist = adapter.getItem(i);
                System.out.println(view);
                test(i);
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void test(int i) {
        //pass checklist items to new activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("checklist", checklists.get(i));
        startActivity(intent);
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
                promptAddChecklist();
                return true;

            case R.id.action_deleteItem:
                //deleteCheckedItems();
                return true;

            case R.id.action_refreshPrices:
                //refreshPrices();
                return true;

            case R.id.action_clearList:
                clearList();
                return true;
        }
        return false;

    }

    public void clearList() {
        checklists.clear();
        adapter.notifyDataSetChanged();
    }

    public void addChecklist(String name) {
        Checklist checklist = new Checklist(name);
        checklists.add(checklist);
        adapter.notifyDataSetChanged();
    }

    public void promptAddChecklist(){
        AddChecklistDialog newFragment = new AddChecklistDialog();
        newFragment.thingy = new AddChecklistDialog.Thingy() {
            @Override
            public void idk(String name) {
                System.out.println("WOW!");
                addChecklist(name);
            }
        };
        newFragment.show(getFragmentManager(), "adding");
    }



}
